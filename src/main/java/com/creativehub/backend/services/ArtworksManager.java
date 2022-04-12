package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.ArtworkCreationDto;
import com.creativehub.backend.services.dto.ArtworkDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtworksManager {
	List<ArtworkDto> getAllArtworks();

	Optional<ArtworkDto> findArtworkById(UUID uuid);

	ResponseEntity<ArtworkDto> saveArtwork(ArtworkDto artworkDto);

	void updateArtwork(UUID id, ArtworkDto artworkDto);

	void deleteArtworkById(UUID id);

	void deleteArtworkCreationById(UUID id);

	ArtworkCreationDto saveArtworkCreation(ArtworkCreationDto artworkCreationDto);
}
