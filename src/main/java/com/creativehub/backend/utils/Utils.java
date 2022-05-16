package com.creativehub.backend.utils;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

public class Utils {
	@NonNull
	public static <T> Page<T> listToPage(@NonNull Pageable pageable, @NonNull List<T> list) {
		int totalAvailableElements = list.size();
		PagedListHolder<T> pagedListHolder = new PagedListHolder<>(list);
		pagedListHolder.setPage(pageable.getPageNumber());
		pagedListHolder.setPageSize(pageable.getPageSize());
		List<T> pageList = pagedListHolder.getPageList();
		return new PageImpl<>(pageList, pageable, totalAvailableElements);
	}

	@NonNull
	public static <T> ParameterizedTypeReference<T> getTypeReference() {
		return new ParameterizedTypeReference<T>() {
		};
	}
}
