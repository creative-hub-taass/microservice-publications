package com.creativehub.backend.controllers;

import com.creativehub.backend.services.PostsManager;
import com.creativehub.backend.services.dto.PostCreationDto;
import com.creativehub.backend.services.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/publications")
public class PostsController {
	private final PostsManager postsManager;

	@GetMapping("/posts/")
	public List<PostDto> getAllPosts() {
		return postsManager.getAllPosts();
	}

	@GetMapping("/-/posts/creator/{uid}")
	public List<PostDto> getAllPostsByCreator(@PathVariable UUID uid) {
		return postsManager.getAllPostsByCreator(uid);
	}

	@PostMapping("/posts/")
	public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
		return postsManager.savePost(postDto);
	}

	@GetMapping("/-/posts/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
		return ResponseEntity.of(postsManager.findPostById(id));
	}

	@PutMapping("/posts/{id}")
	public void updatePost(@PathVariable UUID id, @RequestBody PostDto postDto) {
		postsManager.updatePost(id, postDto);
	}

	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable UUID id) {
		postsManager.deletePostById(id);
	}

	@PostMapping("/posts/creations/")
	public ResponseEntity<PostCreationDto> savePostCreation(@RequestBody PostCreationDto postCreationDto) {
		return ResponseEntity.ok(postsManager.savePostCreation(postCreationDto));
	}

	@DeleteMapping("/posts/creations/{id}")
	public void deletePostCreation(@PathVariable UUID id) {
		postsManager.deletePostCreationById(id);
	}
}
