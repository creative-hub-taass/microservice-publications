package com.creativehub.backend.services.dto;

import com.creativehub.backend.models.enums.CreationType;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class PostDto implements Serializable {
	private final UUID id;
	private final Instant timestamp;
	private final Instant lastUpdate;
	private final List<PostCreationDto> creations;
	private final String title;
	private final String body;

	@Data
	public static class PostCreationDto implements Serializable {
		private final UUID id;
		private final UUID user;
		private final CreationType creationType;
		private final String symbol;
	}
}
