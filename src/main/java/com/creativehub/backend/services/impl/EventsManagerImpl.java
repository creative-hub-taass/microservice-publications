package com.creativehub.backend.services.impl;

import com.creativehub.backend.models.EventCreation;
import com.creativehub.backend.repositories.EventCreationRepository;
import com.creativehub.backend.repositories.EventRepository;
import com.creativehub.backend.services.EventsManager;
import com.creativehub.backend.services.dto.EventCreationDto;
import com.creativehub.backend.services.dto.EventDto;
import com.creativehub.backend.services.mapper.EventCreationMapper;
import com.creativehub.backend.services.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventsManagerImpl implements EventsManager {
	private final EventRepository eventRepository;
	private final EventCreationRepository eventCreationRepository;
	private final EventMapper eventMapper;
	private final EventCreationMapper eventCreationMapper;

	@Override
	public List<EventDto> getAllEvents() {
		return eventRepository.findAllOrdered().stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
	}

	@Override
	public List<EventDto> getAllEventsByCreator(UUID userId) {
		return eventRepository.findAllByCreator(userId).stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
	}

	@Override
	public Optional<EventDto> findEventById(UUID id) {
		return eventRepository.findById(id).map(eventMapper::eventToEventDto);
	}

	@Override
	public ResponseEntity<EventDto> saveEvent(EventDto eventDto) {
		return ResponseEntity.ok(eventMapper.eventToEventDto(eventRepository.save(eventMapper.eventDtoToEvent(eventDto))));
	}

	@Override
	public Optional<EventDto> updateEvent(UUID id, EventDto eventDto) {
		return eventRepository.findById(id).map(event -> {
			eventMapper.updateEventFromEventDto(eventDto, event);
			return eventMapper.eventToEventDto(eventRepository.save(event));
		});
	}

	@Override
	public void deleteEventById(UUID id) {
		eventRepository.findById(id).ifPresent(eventRepository::delete);
	}

	@Override
	public void deleteEventCreationById(UUID id) {
		eventCreationRepository.findById(id).ifPresent(eventCreationRepository::delete);
	}

	@Override
	public EventCreationDto saveEventCreation(EventCreationDto eventCreationDto) {
		return eventCreationMapper.eventCreationToEventCreationDto(eventCreationRepository.save(eventCreationMapper.eventCreationDtoToEventCreation(eventCreationDto)));
	}

	@Override
	public void deleteAllEventsByCreator(UUID id) {
		eventRepository.findAllByCreator(id).forEach(event -> {
			List<EventCreation> creations = event.getCreations();
			if (creations.size() > 1) {
				creations.stream()
						.filter(creation -> creation.getUser() == id)
						.forEach(eventCreationRepository::delete);
			} else {
				eventCreationRepository.deleteAll(creations);
				eventRepository.delete(event);
			}
		});
	}
}
