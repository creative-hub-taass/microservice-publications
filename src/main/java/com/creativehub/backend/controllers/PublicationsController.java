package com.creativehub.backend.controllers;

import com.creativehub.backend.services.PublicationsManager;
import com.creativehub.backend.services.dto.PublicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
