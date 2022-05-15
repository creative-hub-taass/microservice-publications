package com.creativehub.backend.controllers;

import com.creativehub.backend.services.FeedManager;
import com.creativehub.backend.services.dto.PublicationDto;
import com.creativehub.backend.services.dto.PublicationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

import static com.creativehub.backend.utils.PaginatedResultsDiscoverability.addLinkHeaderOnPagedResourceRetrieval;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/publications")
public class FeedController {
	private final FeedManager feedManager;
	@Value("${gateway.url}")
	private String gatewayUrl;

	@GetMapping("/-/feed")
	public List<PublicationDto> getPublicFeed(@RequestParam(value = "limit", required = false) Integer limit) {
		return feedManager.getPublicFeed(limit);
	}

	@GetMapping("/feed/{uid}")
	public List<PublicationDto> getUserFeed(@PathVariable UUID uid, @RequestParam(value = "limit", required = false) Integer limit) {
		return feedManager.getUserFeed(uid, limit);
	}

	@GetMapping("/-/feed/paginated")
	public List<PublicationInfo> getPublicFeedPaginated(Pageable pageable, HttpServletResponse response) {
		Page<PublicationInfo> resultPage = feedManager.getPublicFeed(pageable);
		if (pageable.getPageNumber() > resultPage.getTotalPages()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(gatewayUrl).path("/api/v1/publications/-/feed/paginated");
		addLinkHeaderOnPagedResourceRetrieval(uriBuilder, response, resultPage);
		return resultPage.getContent();
	}
}
