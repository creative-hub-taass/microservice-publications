package com.creativehub.backend.controllers;

import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.dto.ArtworkCreationDto;
import com.creativehub.backend.services.dto.ArtworkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/publications/artworks")
public class ArtworksController {
	private final ArtworksManager artworksManager;

	@GetMapping("/")
	public List<ArtworkDto> getAllArtworks() {
		return artworksManager.getAllArtworks();
	}

	@GetMapping("/creator/{uid}")
	public List<ArtworkDto> getAllArtworksByCreator(@PathVariable UUID uid) {
		return artworksManager.getAllArtworksByCreator(uid);
	}

	@PostMapping("/")
	public ResponseEntity<ArtworkDto> saveArtwork(@RequestBody ArtworkDto artworkDto) {
		return artworksManager.saveArtwork(artworkDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArtworkDto> getArtwork(@PathVariable UUID id) {
		return ResponseEntity.of(artworksManager.findArtworkById(id));
	}

	@PutMapping("/{id}")
	public void updateArtwork(@PathVariable UUID id, @RequestBody ArtworkDto artworkDto) {
		artworksManager.updateArtwork(id, artworkDto);
	}

	@DeleteMapping("/{id}")
	public void deleteArtwork(@PathVariable UUID id) {
		artworksManager.deleteArtworkById(id);
	}

	@PostMapping("/creations/")
	public ResponseEntity<ArtworkCreationDto> saveArtworkCreation(@RequestBody ArtworkCreationDto artworkCreationDto) {
		return ResponseEntity.ok(artworksManager.saveArtworkCreation(artworkCreationDto));
	}

	@DeleteMapping("/creations/{id}")
	public void deleteArtworkCreation(@PathVariable UUID id) {
		artworksManager.deleteArtworkCreationById(id);
	}
}
