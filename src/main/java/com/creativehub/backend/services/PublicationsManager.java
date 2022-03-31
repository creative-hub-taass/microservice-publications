package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.*;
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

	void updateArtwork(UUID id, ArtworkDto postDto);

	void updateEvent(UUID id, EventDto postDto);

	void updatePost(UUID id, PostDto postDto);

	void deleteArtworkById(UUID id);

	void deleteEventById(UUID id);

	void deletePostById(UUID id);

	void deleteArtworkCreationById(UUID id);

	void deleteEventCreationById(UUID id);

	void deletePostCreationById(UUID id);

	ArtworkCreationDto saveArtworkCreation(ArtworkCreationDto artworkCreationDto);

	EventCreationDto saveEventCreation(EventCreationDto eventCreationDto);

	PostCreationDto savePostCreation(PostCreationDto postCreationDto);
}
