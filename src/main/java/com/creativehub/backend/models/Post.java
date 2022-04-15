package com.creativehub.backend.models;

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
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private List<PostCreation> creations = new ArrayList<>();

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "body", nullable = false, columnDefinition = "TEXT")
	private String body;
}