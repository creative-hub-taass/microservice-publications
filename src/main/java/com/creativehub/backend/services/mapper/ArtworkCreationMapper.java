package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.ArtworkCreation;
import com.creativehub.backend.services.dto.ArtworkCreationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ArtworkCreationMapper {
	@Mapping(source = "artworkId", target = "artwork.id")
	ArtworkCreation artworkCreationDtoToArtworkCreation(ArtworkCreationDto artworkCreationDto);

	@Mapping(source = "artwork.id", target = "artworkId")
	ArtworkCreationDto artworkCreationToArtworkCreationDto(ArtworkCreation artworkCreation);

	@Mapping(source = "artworkId", target = "artwork.id")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateArtworkCreationFromArtworkCreationDto(ArtworkCreationDto artworkCreationDto, @MappingTarget ArtworkCreation artworkCreation);
}
