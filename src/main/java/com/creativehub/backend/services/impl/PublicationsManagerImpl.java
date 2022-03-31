package com.creativehub.backend.services.impl;

import com.creativehub.backend.repositories.*;
import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.*;
import com.creativehub.backend.services.mapper.*;
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
	private final ArtworkCreationRepository artworkCreationRepository;
	private final EventCreationRepository eventCreationRepository;
	private final PostCreationRepository postCreationRepository;
	private final ArtworkMapper artworkMapper;
	private final EventMapper eventMapper;
	private final PostMapper postMapper;
	private final ArtworkCreationMapper artworkCreationMapper;
	private final EventCreationMapper eventCreationMapper;
	private final PostCreationMapper postCreationMapper;

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
	public void updateArtwork(UUID id, ArtworkDto artworkDto) {
		artworkRepository.findById(id).ifPresent(artwork -> artworkMapper.updateArtworkFromArtworkDto(artworkDto, artwork));
	}

	@Override
	public void updateEvent(UUID id, EventDto eventDto) {
		eventRepository.findById(id).ifPresent(event -> eventMapper.updateEventFromEventDto(eventDto, event));
	}

	@Override
	public void updatePost(UUID id, PostDto postDto) {
		postRepository.findById(id).ifPresent(post -> postMapper.updatePostFromPostDto(postDto, post));
	}

	@Override
	public void deleteArtworkById(UUID id) {
		artworkRepository.findById(id).ifPresent(artworkRepository::delete);
	}

	@Override
	public void deleteEventById(UUID id) {
		eventRepository.findById(id).ifPresent(eventRepository::delete);
	}

	@Override
	public void deletePostById(UUID id) {
		postRepository.findById(id).ifPresent(postRepository::delete);
	}

	@Override
	public void deleteArtworkCreationById(UUID id) {
		artworkCreationRepository.findById(id).ifPresent(artworkCreationRepository::delete);
	}

	@Override
	public void deleteEventCreationById(UUID id) {
		eventCreationRepository.findById(id).ifPresent(eventCreationRepository::delete);
	}

	@Override
	public void deletePostCreationById(UUID id) {
		postCreationRepository.findById(id).ifPresent(postCreationRepository::delete);
	}

	@Override
	public ArtworkCreationDto saveArtworkCreation(ArtworkCreationDto artworkCreationDto) {
		return artworkCreationMapper.artworkCreationToArtworkCreationDto(artworkCreationRepository.save(artworkCreationMapper.artworkCreationDtoToArtworkCreation(artworkCreationDto)));
	}

	@Override
	public EventCreationDto saveEventCreation(EventCreationDto eventCreationDto) {
		return eventCreationMapper.eventCreationToEventCreationDto(eventCreationRepository.save(eventCreationMapper.eventCreationDtoToEventCreation(eventCreationDto)));
	}

	@Override
	public PostCreationDto savePostCreation(PostCreationDto postCreationDto) {
		return postCreationMapper.postCreationToPostCreationDto(postCreationRepository.save(postCreationMapper.postCreationDtoToPostCreation(postCreationDto)));
	}
}
