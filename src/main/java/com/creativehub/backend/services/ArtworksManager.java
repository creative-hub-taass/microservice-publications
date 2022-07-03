package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.ArtworkCreationDto;
import com.creativehub.backend.services.dto.ArtworkDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArtworksManager {
	List<ArtworkDto> getAllArtworks();

	List<ArtworkDto> getAllArtworksByCreator(UUID userId);

	Optional<ArtworkDto> findArtworkById(UUID uuid);

	ResponseEntity<ArtworkDto> saveArtwork(ArtworkDto artworkDto);

	Optional<ArtworkDto> updateArtwork(UUID id, ArtworkDto artworkDto);

	void deleteArtworkById(UUID id);

	void deleteArtworkCreationById(UUID id);

	ArtworkCreationDto saveArtworkCreation(ArtworkCreationDto artworkCreationDto);

	void deleteAllArtworksByCreator(UUID id);

	void decrementArtworkAvailability(UUID id);

	Optional<ArtworkCreationDto> getArtworkCreation(UUID id);
}
