package com.creativehub.backend.services.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Reduced version on UserDto
 */
@Data
public class UserDto implements Serializable {
	private final UUID id;
	private final String username;
	private final String nickname;
	private final String email;
	private final Set<UUID> inspirerIds;
	private final Set<UUID> fanIds;
}
