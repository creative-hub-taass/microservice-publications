package com.creativehub.backend.services.dto;

import com.creativehub.backend.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Integer.signum;

@Getter
public class PublicationInfo implements Comparable<PublicationInfo> {
	@JsonIgnore
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);
	@JsonIgnore
	private static final int TIME_DIVIDER = 7 * 24 * 60 * 60 * 1000; // WEEK
	@JsonIgnore
	private static final WebClient apiClient = WebClient.create();
	@JsonIgnore
	@Nullable
	private final UserDto user;
	@NonNull
	private final PublicationDto publication;
	@Nullable
	private Integer likes = null;
	@Nullable
	private Boolean userLiked = null;
	@Nullable
	private Boolean creatorsFollowedByUser = null;
	@Nullable
	private List<JsonNode> creators = null;
	@Nullable
	private List<JsonNode> comments = null;
	@Nullable
	private Integer commentsCount = null;

	public PublicationInfo(@Nullable UserDto user, @NonNull PublicationDto publication) {
		this.user = user;
		this.publication = publication;
	}

	private void fetchCreators() {
		List<String> uuids = publication.getCreations().stream()
				.map(CreationDto::getUser)
				.map(UUID::toString)
				.collect(Collectors.toList());
		this.creators = apiClient.post()
				.uri("http://microservice-users:8080/api/v1/users/-/public")
				.bodyValue(uuids)
				.retrieve()
				.bodyToMono(Utils.<List<JsonNode>>getTypeReference())
				.block(REQUEST_TIMEOUT);
	}

	private void fetchComments() {
		this.comments = apiClient.get()
				.uri("http://microservice-interactions:8080/api/v1/interactions/-/comments/" + publication.getId().toString())
				.retrieve()
				.bodyToMono(Utils.<List<JsonNode>>getTypeReference())
				.block(REQUEST_TIMEOUT);
		if (this.comments != null) {
			this.commentsCount = this.comments.size();
		}
	}

	private void fetchCommentsCount() {
		this.commentsCount = apiClient.get()
				.uri("http://microservice-interactions:8080/api/v1/interactions/comments/count/" + publication.getId().toString())
				.retrieve()
				.bodyToMono(Integer.class)
				.block(REQUEST_TIMEOUT);
	}

	private void fetchLikes() {
		this.likes = apiClient.get()
				.uri("http://microservice-interactions:8080/api/v1/interactions/-/likes/count/" + publication.getId().toString())
				.retrieve()
				.bodyToMono(Integer.class)
				.block(REQUEST_TIMEOUT);
	}

	private void fetchLiked() {
		if (user != null) {
			this.userLiked = apiClient.get()
					.uri("http://microservice-interactions:8080/api/v1/interactions/userliked/" + user.getId().toString() + "/" + publication.getId().toString())
					.retrieve()
					.bodyToMono(Boolean.class)
					.block(REQUEST_TIMEOUT);
		}
	}

	private void fetchFollowed() {
		if (user != null) {
			List<UUID> creators = publication.getCreations().stream().map(CreationDto::getUser).collect(Collectors.toList());
			creatorsFollowedByUser = user.getInspirerIds().stream().anyMatch(creators::contains);
		}
	}

	private int calculateScore() {
		int _userLiked = userLiked != null && userLiked ? 1 : 0;
		int _creatorsFollowedByUser = creatorsFollowedByUser != null && creatorsFollowedByUser ? 1 : 0;
		int _likes = (likes != null ? likes : 0) + 1;
		int _comments = (commentsCount != null ? commentsCount : 0) + 1;
		return (_likes + _comments) * (1 + _userLiked + _creatorsFollowedByUser);
	}

	@Override
	public int compareTo(PublicationInfo other) {
		long time = (publication.getTime() / TIME_DIVIDER) - (other.publication.getTime() / TIME_DIVIDER);
		if (time == 0) {
			int score = signum(calculateScore() - other.calculateScore());
			if (score == 0) {
				time = (publication.getTime() % TIME_DIVIDER) - (other.publication.getTime() % TIME_DIVIDER);
				return signum((int) time);
			} else return score;
		} else return signum((int) time);
	}

	public void fetchFull() {
		fetchCreators();
		fetchLikes();
		fetchLiked();
		fetchFollowed();
		fetchComments();
	}

	public void fetchPartial() {
		fetchLikes();
		fetchLiked();
		fetchFollowed();
		fetchCommentsCount();
	}
}
