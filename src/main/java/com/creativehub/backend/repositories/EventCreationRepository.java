package com.creativehub.backend.repositories;

import com.creativehub.backend.models.EventCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface EventCreationRepository extends JpaRepository<EventCreation, UUID> {
	@Modifying
	@Transactional
	@Query("delete from EventCreation e where e.user = :user")
	void deleteAllByUser(UUID user);
}