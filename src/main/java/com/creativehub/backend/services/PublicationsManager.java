package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PublicationDto;

import java.util.List;

public interface PublicationsManager {
	List<PublicationDto> getAllPublications();
}
