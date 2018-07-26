package com.example.springdatajpatips.model;


import com.example.springdatajpatips.enums.Status;
import com.example.springdatajpatips.enums.SubscriptionType;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the company_subscription database table.
 * 
 */
@Entity
@Table(name = "company_subscription")
@Data
@EqualsAndHashCode
@ToString(callSuper = true, exclude = {"subscriptionProducts","subscriptionProductPurchaseHistories"})
public class CompanySubscription extends AuditEntity {

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Company
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="company_id")
	private Company company;

	@Enumerated
	@Column(name = "subscription_type", columnDefinition = "smallint")
	private SubscriptionType subscriptionType;

	@Column(name = "start_ts", columnDefinition="TIMESTAMP")
	private Timestamp startTs;

	@Column(name = "end_ts", columnDefinition="TIMESTAMP")
	private Timestamp endTs;

	@Column(columnDefinition = "smallint")
	private int period;

	@Column(name = "period_type", length = 1)
	private String periodType;

	@Column(name = "is_locked")
	private Boolean isLocked = false;

	@Enumerated
	@Column(columnDefinition = "smallint")
	private Status status;

	// bi-directional many-to-one association to CompanySubscription
	@OneToMany(mappedBy = "companySubscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SubscriptionProduct> subscriptionProducts;

	@OneToMany(mappedBy = "companySubscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SubscriptionProductPurchaseHistory> subscriptionProductPurchaseHistories;

}