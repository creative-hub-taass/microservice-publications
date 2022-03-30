package com.creativehub.backend.services.impl;

import com.creativehub.backend.repositories.ArtworkRepository;
import com.creativehub.backend.repositories.EventRepository;
import com.creativehub.backend.repositories.PostRepository;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.ArtworkDto;
import com.creativehub.backend.services.dto.EventDto;
import com.creativehub.backend.services.dto.PostDto;
import com.creativehub.backend.services.dto.PublicationDto;
import com.creativehub.backend.services.mapper.ArtworkMapper;
import com.creativehub.backend.services.mapper.EventMapper;
import com.creativehub.backend.services.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PublicationsManagerImpl implements PublicationsManager {
	private final ArtworkRepository artworkRepository;
	private final EventRepository eventRepository;
	private final PostRepository postRepository;
	private final ArtworkMapper artworkMapper;
	private final EventMapper eventMapper;
	private final PostMapper postMapper;

	@Override
	public List<PublicationDto> getAllPublications() {
		return Stream.of(getAllArtworks(), getAllEvents(), getAllPosts()).flatMap(Collection::stream).collect(Collectors.toList());
	}

	@Override
	public List<ArtworkDto> getAllArtworks() {
		return artworkRepository.findAll().stream().map(artworkMapper::artworkToArtworkDto).collect(Collectors.toList());
	}

	@Override
	public List<EventDto> getAllEvents() {
		return eventRepository.findAll().stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getAllPosts() {
		return postRepository.findAll().stream().map(postMapper::postToPostDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ArtworkDto> findArtworkById(UUID id) {
		return artworkRepository.findById(id).map(artworkMapper::artworkToArtworkDto);
	}

	@Override
	public Optional<EventDto> findEventById(UUID id) {
		return eventRepository.findById(id).map(eventMapper::eventToEventDto);
	}

	@Override
	public Optional<PostDto> findPostById(UUID id) {
		return postRepository.findById(id).map(postMapper::postToPostDto);
	}

	@Override
	public ResponseEntity<ArtworkDto> saveArtwork(ArtworkDto artworkDto) {
		return ResponseEntity.ok(artworkMapper.artworkToArtworkDto(artworkRepository.save(artworkMapper.artworkDtoToArtwork(artworkDto))));
	}

	@Override
	public ResponseEntity<EventDto> saveEvent(EventDto eventDto) {
		return ResponseEntity.ok(eventMapper.eventToEventDto(eventRepository.save(eventMapper.eventDtoToEvent(eventDto))));
	}

	@Override
	public ResponseEntity<PostDto> savePost(PostDto postDto) {
		return ResponseEntity.ok(postMapper.postToPostDto(postRepository.save(postMapper.postDtoToPost(postDto))));
	}

	@Override
	public Optional<ArtworkDto> deleteArtworkById(UUID id) {
		return findArtworkById(id).map(artworkDto -> {
			artworkRepository.deleteById(artworkDto.getId());
			return artworkDto;
		});
	}

	@Override
	public Optional<EventDto> deleteEventById(UUID id) {
		return findEventById(id).map(eventDto -> {
			eventRepository.deleteById(eventDto.getId());
			return eventDto;
		});
	}

	@Override
	public Optional<PostDto> deletePostById(UUID id) {
		return findPostById(id).map(postDto -> {
			postRepository.deleteById(postDto.getId());
			return postDto;
		});
	}
}
