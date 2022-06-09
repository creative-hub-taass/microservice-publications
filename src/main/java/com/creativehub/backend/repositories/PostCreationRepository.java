package com.creativehub.backend.repositories;

import com.creativehub.backend.models.PostCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface PostCreationRepository extends JpaRepository<PostCreation, UUID> {
	@Modifying
	@Transactional
	@Query("delete from PostCreation p where p.user = :user")
	void deleteAllByUser(UUID user);
}