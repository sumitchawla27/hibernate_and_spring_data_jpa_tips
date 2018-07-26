package com.example.springdatajpatips.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the subscription_product_purchase_history database
 * table.
 * 
 */
@Entity
@Table(name = "subscription_product_purchase_history")
@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionProductPurchaseHistory extends AuditEntity {

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to CompanySubscription
	@ManyToOne
	@JoinColumn(name = "company_subscription_id")
	private CompanySubscription companySubscription;

	// bi-directional many-to-one association to ProductContent
	@ManyToOne
	@JoinColumn(name = "product_content_id")
	private ProductContent productContent;

	@Column(name = "license_count")
	private int licenseCount;

	@Column(name = "start_ts", columnDefinition="TIMESTAMP NULL")
	private Timestamp startTs;

}