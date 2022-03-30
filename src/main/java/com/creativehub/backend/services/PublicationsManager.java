package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.ArtworkDto;
import com.creativehub.backend.services.dto.EventDto;
import com.creativehub.backend.services.dto.PostDto;
import com.creativehub.backend.services.dto.PublicationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationsManager {
	List<PublicationDto> getAllPublications();

	List<ArtworkDto> getAllArtworks();

	List<EventDto> getAllEvents();

	List<PostDto> getAllPosts();

	Optional<ArtworkDto> findArtworkById(UUID uuid);

	Optional<EventDto> findEventById(UUID uuid);

	Optional<PostDto> findPostById(UUID uuid);

	ResponseEntity<ArtworkDto> saveArtwork(ArtworkDto artworkDto);

	ResponseEntity<EventDto> saveEvent(EventDto eventDto);

	ResponseEntity<PostDto> savePost(PostDto postDto);

	Optional<ArtworkDto> deleteArtworkById(UUID id);

	Optional<EventDto> deleteEventById(UUID id);

	Optional<PostDto> deletePostById(UUID id);
}
