package com.creativehub.backend.services.impl;

import com.creativehub.backend.models.Artwork;
import com.creativehub.backend.models.ArtworkCreation;
import com.creativehub.backend.repositories.ArtworkCreationRepository;
import com.creativehub.backend.repositories.ArtworkRepository;
import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.dto.ArtworkCreationDto;
import com.creativehub.backend.services.dto.ArtworkDto;
import com.creativehub.backend.services.mapper.ArtworkCreationMapper;
import com.creativehub.backend.services.mapper.ArtworkMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtworksManagerImpl implements ArtworksManager {
	private final ArtworkRepository artworkRepository;
	private final ArtworkCreationRepository artworkCreationRepository;
	private final ArtworkMapper artworkMapper;
	private final ArtworkCreationMapper artworkCreationMapper;

	@Override
	public List<ArtworkDto> getAllArtworks() {
		return artworkRepository.findAllOrdered().stream().map(artworkMapper::artworkToArtworkDto).collect(Collectors.toList());
	}

	@Override
	public List<ArtworkDto> getAllArtworksByCreator(UUID userId) {
		return artworkRepository.findAllByCreator(userId).stream().map(artworkMapper::artworkToArtworkDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ArtworkDto> findArtworkById(UUID id) {
		return artworkRepository.findById(id).map(artworkMapper::artworkToArtworkDto);
	}

	@Override
	public ResponseEntity<ArtworkDto> saveArtwork(ArtworkDto artworkDto) {
		return ResponseEntity.ok(artworkMapper.artworkToArtworkDto(artworkRepository.save(artworkMapper.artworkDtoToArtwork(artworkDto))));
	}

	@Override
	public Optional<ArtworkDto> updateArtwork(UUID id, ArtworkDto artworkDto) {
		return artworkRepository.findById(id).map(artwork -> {
			artworkMapper.updateArtworkFromArtworkDto(artworkDto, artwork);
			return artworkMapper.artworkToArtworkDto(artworkRepository.save(artwork));
		});
	}

	@Override
	public void deleteArtworkById(UUID id) {
		artworkRepository.findById(id).ifPresent(artworkRepository::delete);
	}

	@Override
	public void deleteArtworkCreationById(UUID id) {
		artworkCreationRepository.findById(id).ifPresent(artworkCreationRepository::delete);
	}

	@Override
	public ArtworkCreationDto saveArtworkCreation(ArtworkCreationDto artworkCreationDto) {
		return artworkCreationMapper.artworkCreationToArtworkCreationDto(artworkCreationRepository.save(artworkCreationMapper.artworkCreationDtoToArtworkCreation(artworkCreationDto)));
	}

	@Override
	public void deleteAllArtworksByCreator(UUID id) {
		List<Artwork> allByCreator = artworkRepository.findAllByCreator(id);
		allByCreator.forEach(artwork -> {
			List<ArtworkCreation> creations = artwork.getCreations();
			if (creations.size() <= 1) {
				artworkRepository.delete(artwork);
			}
		});
		artworkCreationRepository.deleteAllByUser(id);
	}

	public void decrementArtworkAvailability(UUID id) {
		artworkRepository.findById(id).ifPresent(artwork -> {
			int available = artwork.getAvailableCopies();
			if (available > 0) {
				artwork.setAvailableCopies(available - 1);
				artworkRepository.save(artwork);
			}
		});
	}
}
