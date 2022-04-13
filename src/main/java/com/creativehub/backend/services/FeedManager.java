package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PublicationDto;

import java.util.List;
import java.util.UUID;

public interface FeedManager {
	List<PublicationDto> getPublicFeed();

	List<PublicationDto> getUserFeed(UUID userId);
}
