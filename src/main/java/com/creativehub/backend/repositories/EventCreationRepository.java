package com.creativehub.backend.repositories;

import com.creativehub.backend.models.EventCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventCreationRepository extends JpaRepository<EventCreation, UUID> {
}