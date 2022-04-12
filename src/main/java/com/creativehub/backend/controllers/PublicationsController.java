package com.creativehub.backend.controllers;

import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.EventsManager;
import com.creativehub.backend.services.PostsManager;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/publications")
public class PublicationsController {
	private final PublicationsManager publicationsManager;
	private final ArtworksManager artworksManager;
	private final EventsManager eventsManager;
	private final PostsManager postsManager;

	@GetMapping("/")
	public List<PublicationDto> getAllPublications() {
		return publicationsManager.getAllPublications();
	}

	@GetMapping("/artworks/")
	public List<ArtworkDto> getAllArtworks() {
		return artworksManager.getAllArtworks();
	}

	@PostMapping("/artworks/")
	public ResponseEntity<ArtworkDto> saveArtwork(@RequestBody ArtworkDto artworkDto) {
		return artworksManager.saveArtwork(artworkDto);
	}

	@GetMapping("/artworks/{id}")
	public ResponseEntity<ArtworkDto> getArtwork(@PathVariable UUID id) {
		return ResponseEntity.of(artworksManager.findArtworkById(id));
	}

	@PutMapping("/artworks/{id}")
	public void updateArtwork(@PathVariable UUID id, @RequestBody ArtworkDto artworkDto) {
		artworksManager.updateArtwork(id, artworkDto);
	}

	@DeleteMapping("/artworks/{id}")
	public void deleteArtwork(@PathVariable UUID id) {
		artworksManager.deleteArtworkById(id);
	}

	@GetMapping("/events/")
	public List<EventDto> getAllEvents() {
		return eventsManager.getAllEvents();
	}

	@PostMapping("/events/")
	public ResponseEntity<EventDto> saveEvent(@RequestBody EventDto eventDto) {
		return eventsManager.saveEvent(eventDto);
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<EventDto> getEvent(@PathVariable UUID id) {
		return ResponseEntity.of(eventsManager.findEventById(id));
	}

	@PutMapping("/events/{id}")
	public void updateEvent(@PathVariable UUID id, @RequestBody EventDto eventDto) {
		eventsManager.updateEvent(id, eventDto);
	}

	@DeleteMapping("/events/{id}")
	public void deleteEvent(@PathVariable UUID id) {
		eventsManager.deleteEventById(id);
	}

	@GetMapping("/posts/")
	public List<PostDto> getAllPosts() {
		return postsManager.getAllPosts();
	}

	@PostMapping("/posts/")
	public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
		return postsManager.savePost(postDto);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
		return ResponseEntity.of(postsManager.findPostById(id));
	}

	@PutMapping("/posts/{id}")
	public void updatePost(@PathVariable UUID id, @RequestBody PostDto postDto) {
		postsManager.updatePost(id, postDto);
	}

	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable UUID id) {
		postsManager.deletePostById(id);
	}

	@PostMapping("/creations/artwork/")
	public ResponseEntity<ArtworkCreationDto> saveArtworkCreation(@RequestBody ArtworkCreationDto artworkCreationDto) {
		return ResponseEntity.ok(artworksManager.saveArtworkCreation(artworkCreationDto));
	}

	@DeleteMapping("/creations/artwork/{id}")
	public void deleteArtworkCreation(@PathVariable UUID id) {
		artworksManager.deleteArtworkCreationById(id);
	}

	@PostMapping("/creations/event/")
	public ResponseEntity<EventCreationDto> saveEventCreation(@RequestBody EventCreationDto eventCreationDto) {
		return ResponseEntity.ok(eventsManager.saveEventCreation(eventCreationDto));
	}

	@DeleteMapping("/creations/event/{id}")
	public void deleteEventCreation(@PathVariable UUID id) {
		eventsManager.deleteEventCreationById(id);
	}

	@PostMapping("/creations/post/")
	public ResponseEntity<PostCreationDto> savePostCreation(@RequestBody PostCreationDto postCreationDto) {
		return ResponseEntity.ok(postsManager.savePostCreation(postCreationDto));
	}

	@DeleteMapping("/creations/post/{id}")
	public void deletePostCreation(@PathVariable UUID id) {
		postsManager.deletePostCreationById(id);
	}
}
