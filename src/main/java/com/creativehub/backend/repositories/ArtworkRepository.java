package com.creativehub.backend.repositories;

import com.creativehub.backend.models.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface ArtworkRepository extends JpaRepository<Artwork, UUID> {
	@Query("select a from Artwork a order by a.creationDateTime DESC")
	List<Artwork> findAllOrdered();

	@Query("select a from Artwork a where exists (select true from a.creations c where c.user = :creator) order by a.creationDateTime DESC")
	List<Artwork> findAllByCreator(UUID creator);

	@Transactional
	@Modifying
	@Query("delete from Artwork a where exists (select true from a.creations c where c.user = :creator)")
	void deleteAllByCreator(UUID creator);

}
