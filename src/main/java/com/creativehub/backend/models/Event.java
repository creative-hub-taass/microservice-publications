package com.creativehub.backend.models;

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
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	private List<EventCreation> creations = new ArrayList<>();

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(name = "image", nullable = false, columnDefinition = "TEXT")
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