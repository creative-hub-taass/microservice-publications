package com.creativehub.backend.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Reduced version on UserDto
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
	private final UUID id;
	private final String username;
	private final String nickname;
	private final String email;
	private final Set<UUID> inspirerIds;
	private final Set<UUID> fanIds;
}
