package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.PostCreation;
import com.creativehub.backend.services.dto.PostCreationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostCreationMapper {
	@Mapping(source = "postId", target = "post.id")
	PostCreation postCreationDtoToPostCreation(PostCreationDto postCreationDto);

	@Mapping(source = "post.id", target = "postId")
	PostCreationDto postCreationToPostCreationDto(PostCreation postCreation);

	@Mapping(source = "postId", target = "post.id")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updatePostCreationFromPostCreationDto(PostCreationDto postCreationDto, @MappingTarget PostCreation postCreation);
}
