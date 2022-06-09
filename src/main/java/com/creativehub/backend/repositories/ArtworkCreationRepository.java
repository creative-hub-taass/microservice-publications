package com.creativehub.backend.repositories;

import com.creativehub.backend.models.ArtworkCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ArtworkCreationRepository extends JpaRepository<ArtworkCreation, UUID> {
	@Modifying
	@Transactional
	@Query("delete from ArtworkCreation a where a.user = :user")
	void deleteAllByUser(UUID user);
}