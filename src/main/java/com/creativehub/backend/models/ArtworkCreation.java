package com.creativehub.backend.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "artwork_creation")
public class ArtworkCreation extends Creation {
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "artwork_id", nullable = false)
	private Artwork artwork;
}