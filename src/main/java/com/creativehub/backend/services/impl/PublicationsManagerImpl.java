package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.EventsManager;
import com.creativehub.backend.services.PostsManager;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.PublicationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
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

	@Override
	public List<PublicationDto> getAllPublicationsByCreator(UUID userId) {
		return Stream.of(artworksManager.getAllArtworksByCreator(userId), eventsManager.getAllEventsByCreator(userId), postsManager.getAllPostsByCreator(userId))
				.flatMap(Collection::stream)
				.sorted()
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAllPublicationsByCreator(UUID id) {
		try {
			artworksManager.deleteAllArtworksByCreator(id);
			eventsManager.deleteAllEventsByCreator(id);
			postsManager.deleteAllPostsByCreator(id);
		} catch (Exception e) {
			log.error("ERROR", e);
		}
	}
}
