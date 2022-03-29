package com.creativehub.backend.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.net.URL;
import java.time.OffsetDateTime;
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
	private List<EventCreation> creations = new ArrayList<>();

	@Column(name = "name", nullable = false)
	private String name;

	@Lob
	@Column(name = "description", nullable = false)
	private String description;

	@Lob
	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "location_name", nullable = false)
	private String locationName;

	@Embedded
	private Coordinates coordinates;

	@Column(name = "start_date_time", nullable = false)
	private OffsetDateTime startDateTime;

	@Column(name = "end_date_time", nullable = false)
	private OffsetDateTime endDateTime;

	@Column(name = "booking_url")
	private URL bookingURL;
}