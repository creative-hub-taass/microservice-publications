package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.PostCreationDto;
import com.creativehub.backend.services.dto.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostsManager {
	List<PostDto> getAllPosts();

	List<PostDto> getAllPostsByCreator(UUID userId);

	Optional<PostDto> findPostById(UUID uuid);

	ResponseEntity<PostDto> savePost(PostDto postDto);

	Optional<PostDto> updatePost(UUID id, PostDto postDto);

	void deletePostById(UUID id);

	void deletePostCreationById(UUID id);

	PostCreationDto savePostCreation(PostCreationDto postCreationDto);

	void deleteAllPostsByCreator(UUID id);
}
