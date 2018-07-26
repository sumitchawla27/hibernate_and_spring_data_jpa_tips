package com.example.springdatajpatips.enums;

public enum SubscriptionType {
	FREE_TRIAL(0),
	PAID_SUBSCRIPTION(1);

	private int value;

	private SubscriptionType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
