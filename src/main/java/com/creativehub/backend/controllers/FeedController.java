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
@RequestMapping("/api/v1/publications")
public class FeedController {
	private final FeedManager feedManager;

	@GetMapping("/-/feed")
	public List<PublicationDto> getPublicFeed(@RequestParam(value = "limit", required = false) Integer limit) {
		return feedManager.getPublicFeed(limit);
	}

	@GetMapping("/feed/{uid}")
	public List<PublicationDto> getUserFeed(@PathVariable UUID uid, @RequestParam(value = "limit", required = false) Integer limit) {
		return feedManager.getUserFeed(uid, limit);
	}
}
