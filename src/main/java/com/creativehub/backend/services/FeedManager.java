package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PublicationDto;
import com.creativehub.backend.services.dto.PublicationInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface FeedManager {
	List<PublicationDto> getPublicFeed(@Nullable Integer limit);

	List<PublicationDto> getUserFeed(UUID userId, @Nullable Integer limit);

	Page<PublicationInfo> getPublicFeed(Pageable pageable);

	Page<PublicationInfo> getUserFeed(UUID userId, Pageable pageable);

	Page<PublicationInfo> getPublicEventsFeed(Pageable pageable);

	Page<PublicationInfo> getUserEventsFeed(UUID userId, Pageable pageable);
}
