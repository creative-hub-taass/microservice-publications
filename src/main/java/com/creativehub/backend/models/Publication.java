package com.creativehub.backend.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public class Publication {
	@Id
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Setter(AccessLevel.NONE)
	@Column(name = "timestamp", nullable = false, insertable = false, updatable = false)
	@CreationTimestamp
	private Instant timestamp;

	@Setter(AccessLevel.NONE)
	@Column(name = "last_update", nullable = false, insertable = false)
	@UpdateTimestamp
	private Instant lastUpdate;
}