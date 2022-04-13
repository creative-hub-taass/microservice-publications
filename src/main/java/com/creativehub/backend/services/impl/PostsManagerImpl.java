package com.creativehub.backend.services.impl;

import com.creativehub.backend.repositories.PostCreationRepository;
import com.creativehub.backend.repositories.PostRepository;
import com.creativehub.backend.services.PostsManager;
import com.creativehub.backend.services.dto.PostCreationDto;
import com.creativehub.backend.services.dto.PostDto;
import com.creativehub.backend.services.mapper.PostCreationMapper;
import com.creativehub.backend.services.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsManagerImpl implements PostsManager {
	private final PostRepository postRepository;
	private final PostCreationRepository postCreationRepository;
	private final PostMapper postMapper;
	private final PostCreationMapper postCreationMapper;

	@Override
	public List<PostDto> getAllPosts() {
		return postRepository.findAll().stream().map(postMapper::postToPostDto).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getAllPostsByCreator(UUID userId) {
		return postRepository.findAllByCreator(userId).stream().map(postMapper::postToPostDto).collect(Collectors.toList());
	}

	@Override
	public Optional<PostDto> findPostById(UUID id) {
		return postRepository.findById(id).map(postMapper::postToPostDto);
	}

	@Override
	public ResponseEntity<PostDto> savePost(PostDto postDto) {
		return ResponseEntity.ok(postMapper.postToPostDto(postRepository.save(postMapper.postDtoToPost(postDto))));
	}

	@Override
	public void updatePost(UUID id, PostDto postDto) {
		postRepository.findById(id).ifPresent(post -> postMapper.updatePostFromPostDto(postDto, post));
	}

	@Override
	public void deletePostById(UUID id) {
		postRepository.findById(id).ifPresent(postRepository::delete);
	}

	@Override
	public void deletePostCreationById(UUID id) {
		postCreationRepository.findById(id).ifPresent(postCreationRepository::delete);
	}

	@Override
	public PostCreationDto savePostCreation(PostCreationDto postCreationDto) {
		return postCreationMapper.postCreationToPostCreationDto(postCreationRepository.save(postCreationMapper.postCreationDtoToPostCreation(postCreationDto)));
	}
}