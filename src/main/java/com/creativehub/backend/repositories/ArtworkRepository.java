package com.creativehub.backend.repositories;

import com.creativehub.backend.models.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtworkRepository extends JpaRepository<Artwork, UUID> {
}