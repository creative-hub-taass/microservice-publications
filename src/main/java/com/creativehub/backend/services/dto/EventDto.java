package com.creativehub.backend.services.dto;

import com.creativehub.backend.models.enums.CreationType;
import lombok.Data;

import java.io.Serializable;
import java.net.URL;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class EventDto implements Serializable {
	private final UUID id;
	private final Instant timestamp;
	private final Instant lastUpdate;
	private final List<EventCreationDto> creations;
	private final String name;
	private final String description;
	private final String image;
	private final String locationName;
	private final CoordinatesDto coordinates;
	private final OffsetDateTime startDateTime;
	private final OffsetDateTime endDateTime;
	private final URL bookingURL;

	@Data
	public static class EventCreationDto implements Serializable {
		private final UUID id;
		private final UUID user;
		private final CreationType creationType;
		private final String symbol;
	}

	@Data
	public static class CoordinatesDto implements Serializable {
		private final Double latitude;
		private final Double longitude;
	}
}
