package com.example.springdatajpatips.request;

import org.springframework.data.domain.Sort;


public class PagingDTO {
    private int offset = 0;
    private int limit = 10;
    private Sort.Direction order = Sort.Direction.DESC;
    private String sort = "createdTs";


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Sort.Direction getOrder() {
        return order;
    }

    public void setOrder(Sort.Direction order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
