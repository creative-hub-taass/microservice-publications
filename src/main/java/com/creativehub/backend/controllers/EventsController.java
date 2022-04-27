package com.creativehub.backend.controllers;

import com.creativehub.backend.services.EventsManager;
import com.creativehub.backend.services.dto.EventCreationDto;
import com.creativehub.backend.services.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/publications")
public class EventsController {
	private final EventsManager eventsManager;

	@GetMapping("/events/")
	public List<EventDto> getAllEvents() {
		return eventsManager.getAllEvents();
	}

	@GetMapping("/-/events/creator/{uid}")
	public List<EventDto> getAllEventsByCreator(@PathVariable UUID uid) {
		return eventsManager.getAllEventsByCreator(uid);
	}

	@PostMapping("/events/")
	public ResponseEntity<EventDto> saveEvent(@RequestBody EventDto eventDto) {
		return eventsManager.saveEvent(eventDto);
	}

	@GetMapping("/-/events/{id}")
	public ResponseEntity<EventDto> getEvent(@PathVariable UUID id) {
		return ResponseEntity.of(eventsManager.findEventById(id));
	}

	@PutMapping("/events/{id}")
	public void updateEvent(@PathVariable UUID id, @RequestBody EventDto eventDto) {
		eventsManager.updateEvent(id, eventDto);
	}

	@DeleteMapping("/events/{id}")
	public void deleteEvent(@PathVariable UUID id) {
		eventsManager.deleteEventById(id);
	}

	@PostMapping("/events/creations/")
	public ResponseEntity<EventCreationDto> saveEventCreation(@RequestBody EventCreationDto eventCreationDto) {
		return ResponseEntity.ok(eventsManager.saveEventCreation(eventCreationDto));
	}

	@DeleteMapping("/events/creations/{id}")
	public void deleteEventCreation(@PathVariable UUID id) {
		eventsManager.deleteEventCreationById(id);
	}
}
