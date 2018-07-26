package com.example.springdatajpatips.model;


import com.example.springdatajpatips.enums.Status;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the user_license database table.
 * 
 */
@Entity
@Table(name = "user_license")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLicense extends AuditEntity {

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to SubscriptionProduct
	@ManyToOne
	@JoinColumn(name = "subscription_product_id")
	private SubscriptionProduct subscriptionProduct;

	// bi-directional many-to-one association to CompanyUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_user_id")
	private CompanyUser companyUser;
	
	@Column(name = "association_status")
	private Status associationStatus;

	@Column(name = "activated_ts", columnDefinition="TIMESTAMP NULL")
	private Timestamp activatedTs;

	@Column(name = "deactivated_ts", columnDefinition="TIMESTAMP NULL")
	private Timestamp deactivatedTs;

}