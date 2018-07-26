package com.example.springdatajpatips.enums;

public enum ProductContentType {
	TERRITORY_CONTENT(0),
	NAMED_PRODUCT(1);

	private int value;

	private ProductContentType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
