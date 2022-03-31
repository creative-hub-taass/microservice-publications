package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.ArtworkCreation;
import com.creativehub.backend.services.dto.ArtworkCreationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ArtworkCreationMapper {
	ArtworkCreation artworkCreationDtoToArtworkCreation(ArtworkCreationDto artworkCreationDto);

	ArtworkCreationDto artworkCreationToArtworkCreationDto(ArtworkCreation artworkCreation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateArtworkCreationFromArtworkCreationDto(ArtworkCreationDto artworkCreationDto, @MappingTarget ArtworkCreation artworkCreation);
}
