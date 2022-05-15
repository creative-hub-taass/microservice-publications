package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Artwork;
import com.creativehub.backend.models.Event;
import com.creativehub.backend.models.Post;
import com.creativehub.backend.models.Publication;
import com.creativehub.backend.services.dto.PublicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PublicationsMapper {
	private final ArtworkMapper artworkMapper;
	private final EventMapper eventMapper;
	private final PostMapper postMapper;

	public PublicationDto publicationToPublicationDto(Publication publication) {
		if (publication instanceof Artwork)
			return artworkMapper.artworkToArtworkDto((Artwork) publication);
		if (publication instanceof Event)
			return eventMapper.eventToEventDto((Event) publication);
		if (publication instanceof Post)
			return postMapper.postToPostDto((Post) publication);
		else return null;
	}
}
