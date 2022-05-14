package com.creativehub.backend.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.util.*;

@Data
public class ArtworkDto implements Serializable, PublicationDto {
	private final UUID id;
	private final Instant timestamp;
	private final Instant lastUpdate;
	private final List<ArtworkCreationDto> creations;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private final OffsetDateTime creationDateTime;
	private final String name;
	private final String description;
	private final String type;
	private final Integer copies;
	private final Map<String, String> attributes;
	private final Set<String> images;
	private final Boolean onSale;
	private final Double price;
	private final Currency currency;
	private final String paymentEmail;
	private final Integer availableCopies;

	@JsonIgnore
	@Override
	public long getTime() {
		return creationDateTime.getLong(ChronoField.INSTANT_SECONDS);
	}
}
