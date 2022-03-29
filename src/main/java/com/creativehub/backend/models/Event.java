package com.creativehub.backend.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event")
public class Event extends Publication {
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "event", orphanRemoval = true)
	private List<EventCreation> eventCreations = new ArrayList<>();
}