package com.creativehub.backend.controllers;

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

	@GetMapping("/")
	public List<PublicationDto> getAllPublications() {
		return publicationsManager.getAllPublications();
	}

	@GetMapping("/artworks/")
	public List<ArtworkDto> getAllArtworks() {
		return publicationsManager.getAllArtworks();
	}

	@PostMapping("/artworks/")
	public ResponseEntity<ArtworkDto> saveArtwork(@RequestBody ArtworkDto artworkDto) {
		return publicationsManager.saveArtwork(artworkDto);
	}

	@GetMapping("/artworks/{id}")
	public ResponseEntity<ArtworkDto> getArtwork(@PathVariable UUID id) {
		return ResponseEntity.of(publicationsManager.findArtworkById(id));
	}

	@PutMapping("/artworks/{id}")
	public void updateArtwork(@PathVariable UUID id, @RequestBody ArtworkDto artworkDto) {
		publicationsManager.updateArtwork(id, artworkDto);
	}

	@DeleteMapping("/artworks/{id}")
	public void deleteArtwork(@PathVariable UUID id) {
		publicationsManager.deleteArtworkById(id);
	}

	@GetMapping("/events/")
	public List<EventDto> getAllEvents() {
		return publicationsManager.getAllEvents();
	}

	@PostMapping("/events/")
	public ResponseEntity<EventDto> saveEvent(@RequestBody EventDto eventDto) {
		return publicationsManager.saveEvent(eventDto);
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<EventDto> getEvent(@PathVariable UUID id) {
		return ResponseEntity.of(publicationsManager.findEventById(id));
	}

	@PutMapping("/events/{id}")
	public void updateEvent(@PathVariable UUID id, @RequestBody EventDto eventDto) {
		publicationsManager.updateEvent(id, eventDto);
	}

	@DeleteMapping("/events/{id}")
	public void deleteEvent(@PathVariable UUID id) {
		publicationsManager.deleteEventById(id);
	}

	@GetMapping("/posts/")
	public List<PostDto> getAllPosts() {
		return publicationsManager.getAllPosts();
	}

	@PostMapping("/posts/")
	public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
		return publicationsManager.savePost(postDto);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
		return ResponseEntity.of(publicationsManager.findPostById(id));
	}

	@PutMapping("/posts/{id}")
	public void updatePost(@PathVariable UUID id, @RequestBody PostDto postDto) {
		publicationsManager.updatePost(id, postDto);
	}

	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable UUID id) {
		publicationsManager.deletePostById(id);
	}

	@PostMapping("/creations/artwork/")
	public ResponseEntity<ArtworkCreationDto> saveArtworkCreation(@RequestBody ArtworkCreationDto artworkCreationDto) {
		return ResponseEntity.ok(publicationsManager.saveArtworkCreation(artworkCreationDto));
	}

	@DeleteMapping("/creations/artwork/{id}")
	public void deleteArtworkCreation(@PathVariable UUID id) {
		publicationsManager.deleteArtworkCreationById(id);
	}

	@PostMapping("/creations/event/")
	public ResponseEntity<EventCreationDto> saveEventCreation(@RequestBody EventCreationDto eventCreationDto) {
		return ResponseEntity.ok(publicationsManager.saveEventCreation(eventCreationDto));
	}

	@DeleteMapping("/creations/event/{id}")
	public void deleteEventCreation(@PathVariable UUID id) {
		publicationsManager.deleteEventCreationById(id);
	}

	@PostMapping("/creations/post/")
	public ResponseEntity<PostCreationDto> savePostCreation(@RequestBody PostCreationDto postCreationDto) {
		return ResponseEntity.ok(publicationsManager.savePostCreation(postCreationDto));
	}

	@DeleteMapping("/creations/post/{id}")
	public void deletePostCreation(@PathVariable UUID id) {
		publicationsManager.deletePostCreationById(id);
	}
}
