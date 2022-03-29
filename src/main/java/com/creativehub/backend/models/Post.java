package com.creativehub.backend.models;

import lombok.*;

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
@Table(name = "post")
public class Post extends Publication {
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
	private List<PostCreation> postCreations = new ArrayList<>();
}