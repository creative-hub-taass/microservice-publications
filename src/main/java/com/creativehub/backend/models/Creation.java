package com.creativehub.backend.models;

import com.creativehub.backend.models.enums.CreationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public class Creation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "user_id", nullable = false, updatable = false)
	private UUID user;

	@Enumerated(EnumType.STRING)
	@Column(name = "creation_type", nullable = false)
	private CreationType creationType;
}