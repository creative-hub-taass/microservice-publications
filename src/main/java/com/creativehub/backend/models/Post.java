package com.creativehub.backend.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
	private List<PostCreation> creations = new ArrayList<>();

	@Column(name = "title", nullable = false)
	private String title;

	@Lob
	@Column(name = "body", nullable = false)
	private String body;
}