package com.creativehub.backend.repositories;

import com.creativehub.backend.models.PostCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostCreationRepository extends JpaRepository<PostCreation, UUID> {
}