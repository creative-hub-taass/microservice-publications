package com.creativehub.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "artwork")
public class Artwork extends Publication {
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "artwork", orphanRemoval = true)
	private List<ArtworkCreation> artworkCreations = new ArrayList<>();
}