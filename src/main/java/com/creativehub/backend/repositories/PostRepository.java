package com.creativehub.backend.repositories;

import com.creativehub.backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
	@Query("select p from Post p order by p.timestamp DESC")
	List<Post> findAllOrdered();

	@Query("select p from Post p where exists (select true from p.creations c where c.user = :creator) order by p.timestamp DESC")
	List<Post> findAllByCreator(UUID creator);
}