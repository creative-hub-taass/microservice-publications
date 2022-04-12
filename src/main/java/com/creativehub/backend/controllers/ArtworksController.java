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
@RequestMapping("/api/v1/publications")
public class ArtworksController {
	private final ArtworksManager artworksManager;

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

	@PostMapping("/creations/artwork/")
	public ResponseEntity<ArtworkCreationDto> saveArtworkCreation(@RequestBody ArtworkCreationDto artworkCreationDto) {
		return ResponseEntity.ok(artworksManager.saveArtworkCreation(artworkCreationDto));
	}

	@DeleteMapping("/creations/artwork/{id}")
	public void deleteArtworkCreation(@PathVariable UUID id) {
		artworksManager.deleteArtworkCreationById(id);
	}
}
