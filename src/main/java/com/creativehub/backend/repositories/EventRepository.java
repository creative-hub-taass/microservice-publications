package com.creativehub.backend.repositories;

import com.creativehub.backend.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
	@Query("select e from Event e order by e.startDateTime DESC")
	List<Event> findAllOrdered();

	@Query("select e from Event e where exists (select true from e.creations c where c.user = :creator) order by e.startDateTime DESC")
	List<Event> findAllByCreator(UUID creator);
}