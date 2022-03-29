package com.creativehub.backend.services.dto;

import com.creativehub.backend.models.enums.CreationType;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

@Data
public class ArtworkDto implements Serializable {
	private final UUID id;
	private final Instant timestamp;
	private final Instant lastUpdate;
	private final List<ArtworkCreationDto> creations;
	private final OffsetDateTime creationDateTime;
	private final String name;
	private final String description;
	private final String type;
	private final String size;
	private final Integer copies;
	private final Map<String, String> attributes;
	private final Set<String> images;
	private final Boolean onSale;
	private final Double price;
	private final Currency currency;
	private final Integer availableCopies;

	@Data
	public static class ArtworkCreationDto implements Serializable {
		private final UUID id;
		private final UUID user;
		private final CreationType creationType;
		private final String symbol;
	}
}
