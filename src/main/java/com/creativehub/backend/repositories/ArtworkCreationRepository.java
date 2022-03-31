package com.creativehub.backend.repositories;

import com.creativehub.backend.models.ArtworkCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtworkCreationRepository extends JpaRepository<ArtworkCreation, UUID> {
}