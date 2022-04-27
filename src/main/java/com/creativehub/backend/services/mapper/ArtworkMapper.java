package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Artwork;
import com.creativehub.backend.models.ArtworkCreation;
import com.creativehub.backend.services.dto.ArtworkCreationDto;
import com.creativehub.backend.services.dto.ArtworkDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ArtworkMapper {
	Artwork artworkDtoToArtwork(ArtworkDto artworkDto);

	ArtworkDto artworkToArtworkDto(Artwork artwork);

	@Mapping(source = "artworkId", target = "artwork.id")
	ArtworkCreation artworkCreationDtoToArtworkCreation(ArtworkCreationDto artworkCreationDto);

	@Mapping(source = "artwork.id", target = "artworkId")
	ArtworkCreationDto artworkCreationToArtworkCreationDto(ArtworkCreation artworkCreation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateArtworkFromArtworkDto(ArtworkDto artworkDto, @MappingTarget Artwork artwork);
}
