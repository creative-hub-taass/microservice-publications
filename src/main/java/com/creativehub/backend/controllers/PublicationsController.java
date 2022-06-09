package com.creativehub.backend.controllers;

import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.PublicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/publications")
public class PublicationsController {
	private final PublicationsManager publicationsManager;

	@GetMapping("/")
	public List<PublicationDto> getAllPublications() {
		return publicationsManager.getAllPublications();
	}

	@GetMapping("/creator/{uid}")
	public List<PublicationDto> getAllPublicationsByCreator(@PathVariable UUID uid) {
		return publicationsManager.getAllPublicationsByCreator(uid);
	}

	@DeleteMapping("/creator/{uid}")
	public void deleteAllPublicationsByCreator(@PathVariable UUID uid) {
		publicationsManager.deleteAllPublicationsByCreator(uid);
	}
}
