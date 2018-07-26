package com.example.springdatajpatips.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the subscription_product database table.
 * 
 */
@Entity
@Table(name = "subscription_product")
@Data
@ToString(exclude = "userLicenses")
@EqualsAndHashCode(callSuper = true)
public class SubscriptionProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to CompanySubscription
	@ManyToOne
	@JoinColumn(name = "company_subscription_id")
	private CompanySubscription companySubscription;

	@ManyToOne
	@JoinColumn(name = "product_content_id")
	private ProductContent productContent;
	
	@Column(name = "license_count")
	private int licenseCount;

	// bi-directional many-to-one association to UserLicense
	@OneToMany(mappedBy = "subscriptionProduct", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserLicense> userLicenses;

}