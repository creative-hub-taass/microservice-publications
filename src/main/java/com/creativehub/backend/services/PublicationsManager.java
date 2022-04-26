package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PublicationDto;

import java.util.List;
import java.util.UUID;

public interface PublicationsManager {
	List<PublicationDto> getAllPublications();

	List<PublicationDto> getAllPublicationsByCreator(UUID userId);
}
