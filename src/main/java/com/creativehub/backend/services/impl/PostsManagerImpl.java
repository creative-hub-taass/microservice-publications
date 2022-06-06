package com.creativehub.backend.services.impl;

import com.creativehub.backend.models.PostCreation;
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
		return postRepository.findAllOrdered().stream().map(postMapper::postToPostDto).collect(Collectors.toList());
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
	public Optional<PostDto> updatePost(UUID id, PostDto postDto) {
		return postRepository.findById(id).map(post -> {
			postMapper.updatePostFromPostDto(postDto, post);
			return postMapper.postToPostDto(postRepository.save(post));
		});
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

	@Override
	public void deleteAllPostsByCreator(UUID id) {
		postRepository.findAllByCreator(id).forEach(post -> {
			List<PostCreation> creations = post.getCreations();
			if (creations.size() > 1) {
				creations.stream()
						.filter(creation -> creation.getUser() == id)
						.forEach(postCreationRepository::delete);
			} else {
				postCreationRepository.deleteAll(creations);
				postRepository.delete(post);
			}
		});
	}
}
