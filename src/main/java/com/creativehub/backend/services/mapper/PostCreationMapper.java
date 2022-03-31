package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.PostCreation;
import com.creativehub.backend.services.dto.PostCreationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostCreationMapper {
	PostCreation postCreationDtoToPostCreation(PostCreationDto postCreationDto);

	PostCreationDto postCreationToPostCreationDto(PostCreation postCreation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updatePostCreationFromPostCreationDto(PostCreationDto postCreationDto, @MappingTarget PostCreation postCreation);
}
