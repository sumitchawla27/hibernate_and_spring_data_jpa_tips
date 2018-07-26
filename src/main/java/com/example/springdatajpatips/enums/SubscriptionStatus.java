package com.example.springdatajpatips.enums;

public enum SubscriptionStatus {
	ALL(0),
	ACTIVE_FREE_TRIAL_OR_PAID_SUBSCRIPTIONS(1),
	ACTIVE_PAID_SUBSCRIPTIONS(2),
	ACTIVE_FREE_TRIAL(3),
	EXPIRED_FREE_TRIAL(4),
	EXPIRED_PAID_SUBSCRIPTIONS(5),
	FUTURE_SUBSCRIPTIONS(6);

	private int value;

	private SubscriptionStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
