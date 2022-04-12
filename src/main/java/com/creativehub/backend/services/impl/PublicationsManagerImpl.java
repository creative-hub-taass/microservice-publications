package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.EventsManager;
import com.creativehub.backend.services.PostsManager;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.PublicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PublicationsManagerImpl implements PublicationsManager {
	private final ArtworksManager artworksManager;
	private final EventsManager eventsManager;
	private final PostsManager postsManager;

	@Override
	public List<PublicationDto> getAllPublications() {
		return Stream.of(artworksManager.getAllArtworks(), eventsManager.getAllEvents(), postsManager.getAllPosts())
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}
}
