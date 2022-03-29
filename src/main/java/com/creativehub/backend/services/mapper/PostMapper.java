package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Post;
import com.creativehub.backend.services.dto.PostDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostMapper {
	Post postDtoToPost(PostDto postDto);

	PostDto postToPostDto(Post post);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updatePostFromPostDto(PostDto postDto, @MappingTarget Post post);
}
