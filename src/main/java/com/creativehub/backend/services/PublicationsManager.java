package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PublicationDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface PublicationsManager {
	List<PublicationDto> getAllPublications();

	List<PublicationDto> getAllPublications(@Nullable Integer limit);

	List<PublicationDto> getAllPublicationsByCreator(UUID userId);
}
