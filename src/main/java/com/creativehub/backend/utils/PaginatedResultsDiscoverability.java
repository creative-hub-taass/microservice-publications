package com.creativehub.backend.utils;

import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PaginatedResultsDiscoverability {
	public static void addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, Page<?> page) {
		final ArrayList<String> headers = new ArrayList<>();
		int pageNumber = page.getNumber();
		int totalPages = page.getTotalPages();
		int pageSize = page.getPageable().getPageSize();
		if (page.hasNext()) {
			final String uriForNextPage = constructNextPageUri(uriBuilder, pageNumber, pageSize);
			headers.add(createLinkHeader(uriForNextPage, "next"));
		}
		if (page.hasPrevious()) {
			final String uriForPrevPage = constructPrevPageUri(uriBuilder, pageNumber, pageSize);
			headers.add(createLinkHeader(uriForPrevPage, "prev"));
		}
		if (hasFirstPage(page)) {
			final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
			headers.add(createLinkHeader(uriForFirstPage, "first"));
		}
		if (hasLastPage(page)) {
			final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
			headers.add(createLinkHeader(uriForLastPage, "last"));
		}
		for (String header : headers) {
			response.addHeader("Link", header);
		}
	}

	static String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
		return uriBuilder.replaceQueryParam("page", page + 1).replaceQueryParam("size", size).build().encode().toUriString();
	}

	static String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
		return uriBuilder.replaceQueryParam("page", page - 1).replaceQueryParam("size", size).build().encode().toUriString();
	}

	static String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
		return uriBuilder.replaceQueryParam("page", 0).replaceQueryParam("size", size).build().encode().toUriString();
	}

	static String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int size) {
		return uriBuilder.replaceQueryParam("page", totalPages).replaceQueryParam("size", size).build().encode().toUriString();
	}

	static boolean hasFirstPage(Page<?> page) {
		return page.hasPrevious();
	}

	static boolean hasLastPage(Page<?> page) {
		return page.getTotalPages() > 1 && page.hasNext();
	}

	public static String createLinkHeader(final String uri, final String rel) {
		return "<" + uri + ">; rel=\"" + rel + "\"";
	}
}
