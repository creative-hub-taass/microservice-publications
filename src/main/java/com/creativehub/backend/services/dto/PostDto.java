package com.creativehub.backend.services.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class PostDto implements Serializable, PublicationDto {
	private final UUID id;
	private final Instant timestamp;
	private final Instant lastUpdate;
	private final List<PostCreationDto> creations;
	private final String title;
	private final String body;

	@Override
	public long getTime() {
		return lastUpdate.getEpochSecond();
	}
}
