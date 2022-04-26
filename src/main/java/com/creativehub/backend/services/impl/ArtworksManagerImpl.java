package com.creativehub.backend.services.impl;

import com.creativehub.backend.repositories.ArtworkCreationRepository;
import com.creativehub.backend.repositories.ArtworkRepository;
import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.dto.ArtworkCreationDto;
import com.creativehub.backend.services.dto.ArtworkDto;
import com.creativehub.backend.services.mapper.ArtworkCreationMapper;
import com.creativehub.backend.services.mapper.ArtworkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
	public void updateArtwork(UUID id, ArtworkDto artworkDto) {
		artworkRepository.findById(id).ifPresent(artwork -> artworkMapper.updateArtworkFromArtworkDto(artworkDto, artwork));
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
		artworkRepository.deleteAllByCreator(id);
	}
}
