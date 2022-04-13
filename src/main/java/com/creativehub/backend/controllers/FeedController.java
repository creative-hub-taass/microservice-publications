package com.creativehub.backend.controllers;

import com.creativehub.backend.services.FeedManager;
import com.creativehub.backend.services.dto.PublicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/publications/feed")
public class FeedController {
	private final FeedManager feedManager;

	@GetMapping("/")
	public List<PublicationDto> getPublicFeed() {
		return feedManager.getPublicFeed();
	}

	@GetMapping("/{uid}")
	public List<PublicationDto> getUserFeed(@PathVariable UUID uid) {
		return feedManager.getUserFeed(uid);
	}
}