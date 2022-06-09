package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PublicationDto;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PublicationsManager {
	List<PublicationDto> getAllPublications();

	List<PublicationDto> getAllPublicationsByCreator(UUID userId);

	@Transactional(isolation = Isolation.SERIALIZABLE)
	void deleteAllPublicationsByCreator(UUID id);
}
