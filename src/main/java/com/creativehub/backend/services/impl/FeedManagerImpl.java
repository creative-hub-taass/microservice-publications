package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.FeedManager;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.PublicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FeedManagerImpl implements FeedManager {
	private final PublicationsManager publicationsManager;

	@Override
	public List<PublicationDto> getPublicFeed() {
		return publicationsManager.getAllPublications();
	}

	@Override
	public List<PublicationDto> getUserFeed(UUID userId) {
		return Collections.emptyList(); // TODO implement
	}
}
