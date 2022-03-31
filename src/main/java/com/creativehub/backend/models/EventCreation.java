package com.creativehub.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event_creation")
public class EventCreation extends Creation {
	@Column(name = "event_id", nullable = false, updatable = false)
	private UUID eventId;
}