package com.creativehub.backend.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Reduced version on UserDto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
	private UUID id;
	private String username;
	private String nickname;
	private String email;
	private Set<UUID> inspirerIds;
	private Set<UUID> fanIds;
}
