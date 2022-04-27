package com.creativehub.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "artwork_creation")
public class ArtworkCreation extends Creation {
	@ToString.Exclude
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artwork_id", nullable = false)
	private Artwork artwork;
}