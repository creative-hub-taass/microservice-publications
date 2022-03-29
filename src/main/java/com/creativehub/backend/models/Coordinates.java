package com.creativehub.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@Embeddable
public class Coordinates {
	@Column(name = "lat", nullable = false)
	private Double latitude;

	@Column(name = "lon", nullable = false)
	private Double longitude;
}