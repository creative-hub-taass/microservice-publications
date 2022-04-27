package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Post;
import com.creativehub.backend.models.PostCreation;
import com.creativehub.backend.services.dto.PostCreationDto;
import com.creativehub.backend.services.dto.PostDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostMapper {
	Post postDtoToPost(PostDto postDto);

	PostDto postToPostDto(Post post);

	@Mapping(source = "postId", target = "post.id")
	PostCreation postCreationDtoToPostCreation(PostCreationDto postCreationDto);

	@Mapping(source = "post.id", target = "postId")
	PostCreationDto postCreationToPostCreationDto(PostCreation postCreation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updatePostFromPostDto(PostDto postDto, @MappingTarget Post post);
}
