package com.example.springdatajpatips.util;

import org.springframework.data.domain.PageRequest;

public class PageRequestUtility {

	public static PageRequest createPageRequest(com.example.springdatajpatips.request.PagingDTO pageRequest) {
		PageRequest request;
		if (pageRequest.getSort() != null && !pageRequest.getSort().trim().isEmpty()) {
			request = PageRequest.of(pageRequest.getOffset(), pageRequest.getLimit(), pageRequest.getOrder(), pageRequest.getSort());
		} else {
			request = PageRequest.of(pageRequest.getOffset(), pageRequest.getLimit());
		}
		return request;
	}


}
