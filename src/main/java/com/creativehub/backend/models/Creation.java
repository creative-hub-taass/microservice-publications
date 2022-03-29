package com.creativehub.backend.models;

import com.creativehub.backend.models.enums.CreationType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public class Creation {
	@Id
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "user_id", nullable = false)
	private UUID user;

	@Enumerated(EnumType.STRING)
	@Column(name = "creation_type", nullable = false)
	private CreationType creationType;

	@Column(name = "symbol")
	private String symbol;
}