package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.EventCreationDto;
import com.creativehub.backend.services.dto.EventDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventsManager {
	List<EventDto> getAllEvents();

	List<EventDto> getAllEventsByCreator(UUID userId);

	Optional<EventDto> findEventById(UUID uuid);

	ResponseEntity<EventDto> saveEvent(EventDto eventDto);

	Optional<EventDto> updateEvent(UUID id, EventDto postDto);

	void deleteEventById(UUID id);

	void deleteEventCreationById(UUID id);

	EventCreationDto saveEventCreation(EventCreationDto eventCreationDto);

	void deleteAllEventsByCreator(UUID id);
}
