package com.example.springdatajpatips.response;

import java.util.List;

import lombok.Data;

@Data
public class GenericListDTO<T> {

	private List<T> records;

	private Long totalCount;


}
