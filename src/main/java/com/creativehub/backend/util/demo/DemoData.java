package com.creativehub.backend.util.demo;

import com.creativehub.backend.services.ArtworksManager;
import com.creativehub.backend.services.dto.ArtworkDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DemoData {
	private static final String CLIENT_ID = "f31d4faf871963e30f66";
	private static final String CLIENT_SECRET = "2b56cd64b94facd6356cc623dd789048";
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);
	private final ArtworksManager artworksManager;
	private final WebClient apiClient = WebClient.create();
	@Value("#{new Boolean('${demo.enable}')}")
	private Boolean enable;
	@Value("#{new Integer('${demo.count}')}")
	private Integer count;

	@EventListener
	public void appReady(ApplicationReadyEvent ignored) {
		if (!enable) return;
		Token t = apiClient.post()
				.uri("https://api.artsy.net/api/tokens/xapp_token")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("{\"client_id\": \"" + CLIENT_ID + "\", \"client_secret\": \"" + CLIENT_SECRET + "\"}")
				.retrieve()
				.bodyToMono(Token.class)
				.block(REQUEST_TIMEOUT);
		String token = t != null ? t.token : null;
		if (token != null) {
			requestData(token, "https://api.artsy.net/api/artworks?size=" + count, 1);
		}
		log.info("Loaded sample data");
	}

	private void requestData(String token, String href, int max) {
		if (max == 0 || href == null) return;
		ArtworksPagination pagination = apiClient.get()
				.uri(href)
				.header("X-XAPP-Token", token)
				.retrieve()
				.bodyToMono(ArtworksPagination.class)
				.doOnError(WebClientResponseException.class, throwable -> {
					log.error("WebClientResponseException: {}", throwable.getResponseBodyAsString());
					log.error("WebClientResponseException", throwable);
				})
				.onErrorResume(WebClientResponseException.class, e -> Mono.empty())
				.block(REQUEST_TIMEOUT);
		if (pagination != null) {
			String next = loadData(pagination);
			requestData(token, next, max - 1);
		}
	}

	private String loadData(ArtworksPagination pagination) {
		pagination._embedded.artworks.forEach(it -> {
			log.info(it.toString());
			boolean unique = it.unique != null && it.unique;
			int copies = unique ? 1 : new Random().nextInt(8) + 2;
			ArtworkDto artworkDto = new ArtworkDto(
					null,
					it.created_at, it.updated_at, null,
					OffsetDateTime.ofInstant(it.created_at, ZoneId.systemDefault()),
					it.title, it.collecting_institution,
					it.category, it.dimensions.cm.text,
					copies,
					null, null,
					it.can_acquire, Math.random() * 990 + 10, Currency.getInstance("EUR"),
					it.can_acquire ? "test@example.com" : null,
					it.can_acquire ? copies : 0
			);
			artworksManager.saveArtwork(artworkDto);
		});
		return pagination._links.next.href;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class Token {
		String token;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class ArtworksPagination {
		ArtworksList _embedded;
		Links _links;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class Links {
		Link self;
		Link next;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class Link {
		String href;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class ArtworksList {
		List<ArtsyArtwork> artworks;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class ArtsyArtwork {
		String id;
		String slug;
		Instant created_at;
		Instant updated_at;
		String title;
		String category;
		String medium;
		String date;
		Dimensions dimensions;
		Boolean published;
		String exhibition_history;
		String collecting_institution;
		String additional_information;
		Boolean unique;
		Boolean can_acquire;
		Boolean sold;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class Dimensions {
		DimensionsCm cm;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class DimensionsCm {
		String text;
	}
}
