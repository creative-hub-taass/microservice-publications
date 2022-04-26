package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PublicationDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface FeedManager {
	List<PublicationDto> getPublicFeed(@Nullable Integer limit);

	List<PublicationDto> getUserFeed(UUID userId, @Nullable Integer limit);
}
