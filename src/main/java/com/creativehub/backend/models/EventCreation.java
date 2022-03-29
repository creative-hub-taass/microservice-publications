package com.creativehub.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event_creation")
public class EventCreation extends Creation {
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;
}