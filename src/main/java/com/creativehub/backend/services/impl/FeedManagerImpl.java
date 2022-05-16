package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.FeedManager;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.PublicationDto;
import com.creativehub.backend.services.dto.PublicationInfo;
import com.creativehub.backend.services.dto.UserDto;
import com.creativehub.backend.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedManagerImpl implements FeedManager {
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);
	private static final WebClient apiClient = WebClient.create();
	private final PublicationsManager publicationsManager;

	@Override
	public List<PublicationDto> getPublicFeed(@Nullable Integer limit) {
		return publicationsManager.getAllPublications().stream()
				.map(publication -> new PublicationInfo(null, publication))
				.peek(PublicationInfo::fetchPartial)
				.sorted(Comparator.reverseOrder())
				.limit(limit != null ? limit : Integer.MAX_VALUE)
				.map(PublicationInfo::getPublication)
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
				.map(publication -> new PublicationInfo(user, publication))
				.peek(PublicationInfo::fetchPartial)
				.sorted(Comparator.reverseOrder())
				.limit(limit != null ? limit : Integer.MAX_VALUE)
				.map(PublicationInfo::getPublication)
				.collect(Collectors.toList());
	}

	@Override
	public Page<PublicationInfo> getPublicFeed(Pageable pageable) {
		return getPublicationsPaged(pageable, null);
	}

	@Override
	public Page<PublicationInfo> getUserFeed(UUID userId, Pageable pageable) {
		UserDto user = apiClient.get()
				.uri("http://microservice-users:8080/api/v1/users/" + userId.toString())
				.retrieve()
				.bodyToMono(UserDto.class)
				.onErrorResume(WebClientResponseException.NotFound.class, e -> {
					e.printStackTrace();
					return Mono.empty();
				})
				.block(REQUEST_TIMEOUT);
		return getPublicationsPaged(pageable, user);
	}

	@NonNull
	private Page<PublicationInfo> getPublicationsPaged(@NonNull Pageable pageable, @Nullable UserDto user) {
		List<PublicationInfo> publications = publicationsManager.getAllPublications().stream()
				.map(publication -> new PublicationInfo(user, publication))
				.peek(PublicationInfo::fetchPartial)
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		return Utils.listToPage(pageable, publications, PublicationInfo::fetchFull);
	}
}
