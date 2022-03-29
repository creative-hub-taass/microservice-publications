package com.creativehub.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "post_creation")
public class PostCreation extends Creation {
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
}