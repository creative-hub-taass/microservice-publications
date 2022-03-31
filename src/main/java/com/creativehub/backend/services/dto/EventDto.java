package com.creativehub.backend.services.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.net.URL;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class EventDto implements Serializable, PublicationDto {
	private final UUID id;
	private final Instant timestamp;
	private final Instant lastUpdate;
	private final List<EventCreationDto> creations;
	private final String name;
	private final String description;
	private final String image;
	private final String locationName;
	private final CoordinatesDto coordinates;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private final OffsetDateTime startDateTime;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private final OffsetDateTime endDateTime;
	private final URL bookingURL;

	@Data
	public static class CoordinatesDto implements Serializable {
		private final Double latitude;
		private final Double longitude;
	}
}
