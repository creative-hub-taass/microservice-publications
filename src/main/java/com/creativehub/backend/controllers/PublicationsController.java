package com.creativehub.backend.controllers;

import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.ArtworkDto;
import com.creativehub.backend.services.dto.EventDto;
import com.creativehub.backend.services.dto.PostDto;
import com.creativehub.backend.services.dto.PublicationDto;
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

	@DeleteMapping("/artworks/{id}")
	public ResponseEntity<ArtworkDto> deleteArtwork(@PathVariable UUID id) {
		return ResponseEntity.of(publicationsManager.deleteArtworkById(id));
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

	@DeleteMapping("/events/{id}")
	public ResponseEntity<EventDto> deleteEvent(@PathVariable UUID id) {
		return ResponseEntity.of(publicationsManager.deleteEventById(id));
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

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<PostDto> deletePost(@PathVariable UUID id) {
		return ResponseEntity.of(publicationsManager.deletePostById(id));
	}
}
