package com.creativehub.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "artwork_creation")
public class ArtworkCreation extends Creation {
}