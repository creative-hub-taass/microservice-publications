package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.FeedManager;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.CreationDto;
import com.creativehub.backend.services.dto.PublicationDto;
import com.creativehub.backend.services.dto.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Integer.signum;

@RequiredArgsConstructor
@Service
public class FeedManagerImpl implements FeedManager {
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);
	private static final WebClient apiClient = WebClient.create();
	private final PublicationsManager publicationsManager;

	@Override
	public List<PublicationDto> getPublicFeed(@Nullable Integer limit) {
		return publicationsManager.getAllPublications().stream()
				.map(publication -> new PublicationWithMetadata(null, publication))
				.sorted()
				.limit(limit != null ? limit : Integer.MAX_VALUE)
				.map(PublicationWithMetadata::getPublication)
				.collect(Collectors.toList());
	}

	@Override
	public List<PublicationDto> getUserFeed(UUID userId, @Nullable Integer limit) {
		UserDto user = apiClient.get()
				.uri("http://microservice-users:8080/api/v1/users/" + userId.toString())
				.retrieve()
				.bodyToMono(UserDto.class)
				.onErrorResume(WebClientResponseException.NotFound.class, e -> {
					e.printStackTrace();
					return Mono.empty();
				})
				.block(REQUEST_TIMEOUT);
		return publicationsManager.getAllPublications().stream()
				.map(publication -> new PublicationWithMetadata(user, publication))
				.sorted()
				.limit(limit != null ? limit : Integer.MAX_VALUE)
				.map(PublicationWithMetadata::getPublication)
				.collect(Collectors.toList());
	}

	@Getter
	private static class PublicationWithMetadata implements Comparable<PublicationWithMetadata> {
		private static final int TIME_DIVIDER = 7 * 24 * 60 * 60 * 1000; // WEEk
		@Nullable
		private final UserDto user;
		private final PublicationDto publication;
		private int likes;
		private boolean liked;
		private boolean followed;

		private PublicationWithMetadata(@Nullable UserDto user, PublicationDto publication) {
			this.user = user;
			this.publication = publication;
			getMetadata();
		}

		private void getMetadata() {
			getLikes();
			getLiked();
			getFollowed();
		}

		private void getLikes() {
			Integer likes = apiClient.get()
					.uri("http://microservice-interactions:8080/api/v1/interactions/-/likes/count/" + publication.getId().toString())
					.retrieve()
					.bodyToMono(Integer.class)
					.block(REQUEST_TIMEOUT);
			if (likes != null) this.likes = likes;
		}

		private void getLiked() {
			if (user != null) {
				Boolean liked = apiClient.get()
						.uri("http://microservice-interactions:8080/api/v1/interactions/userliked/" + user.getId().toString() + "/" + publication.getId().toString())
						.retrieve()
						.bodyToMono(Boolean.class)
						.block(REQUEST_TIMEOUT);
				if (liked != null) this.liked = liked;
			}
		}

		private void getFollowed() {
			if (user != null) {
				List<UUID> creators = publication.getCreations().stream().map(CreationDto::getUser).collect(Collectors.toList());
				followed = user.getInspirerIds().stream().anyMatch(creators::contains);
			}
		}

		private int getScore() {
			return (likes + 1) * (1 + (liked ? 1 : 0) + (followed ? 1 : 0));
		}

		@Override
		public int compareTo(PublicationWithMetadata other) {
			long time = (publication.getTime() / TIME_DIVIDER) - (other.publication.getTime() / TIME_DIVIDER);
			if (time == 0) {
				int score = signum(getScore() - other.getScore());
				if (score == 0) {
					time = (publication.getTime() % TIME_DIVIDER) - (other.publication.getTime() % TIME_DIVIDER);
					return signum((int) time);
				} else return score;
			} else return signum((int) time);
		}
	}
}
