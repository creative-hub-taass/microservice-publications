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

import static java.lang.Math.signum;

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
					.uri("http://microservice-interactions:8080/api/v1/interactions/likes/count/" + publication.getId().toString())
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

		@Override
		public int compareTo(PublicationWithMetadata other) {
			int pubCompare = publication.compareTo(other.publication);
			int likesCompare = likes - other.likes;
			int likedCompare = (liked ? 1 : -1) - (other.liked ? 1 : -1);
			int followedCompare = (followed ? 1 : -1) - (other.followed ? 1 : -1);
			return (int) (1000.0 * (
					signum(pubCompare) + signum(likesCompare) + signum(likedCompare) + signum(followedCompare)
			));
		}
	}
}
