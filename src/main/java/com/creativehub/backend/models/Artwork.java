package com.creativehub.backend.models;

import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "artwork")
@Check(constraints = "((on_sale = FALSE) OR (price IS NOT NULL)) AND ((price IS NULL) = (currency IS NULL))")
public class Artwork extends Publication {
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "artwork_id")
	private List<ArtworkCreation> creations = new ArrayList<>();

	@Column(name = "creation_date_time", nullable = false)
	private OffsetDateTime creationDateTime;

	@Column(name = "name", nullable = false)
	private String name;

	@Lob
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "copies", nullable = false)
	private Integer copies;

	@ToString.Exclude
	@ElementCollection
	@MapKeyColumn(name = "name")
	@Column(name = "attribute", nullable = false)
	@CollectionTable(name = "artwork_attributes", joinColumns = @JoinColumn(name = "owner_id"))
	private Map<String, String> attributes = new HashMap<>();

	@ToString.Exclude
	@ElementCollection
	@Column(name = "image", nullable = false)
	@CollectionTable(name = "artwork_images", joinColumns = @JoinColumn(name = "owner_id"))
	private Set<String> images = new LinkedHashSet<>();

	@Column(name = "on_sale", nullable = false)
	private Boolean onSale = false;

	@Column(name = "price")
	private Double price;

	@Column(name = "currency")
	private Currency currency;

	@Column(name = "payment_email")
	private String paymentEmail;

	@Column(name = "available_copies", nullable = false)
	private Integer availableCopies;
}