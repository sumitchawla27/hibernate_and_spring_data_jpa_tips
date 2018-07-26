package com.example.springdatajpatips.model;


import com.example.springdatajpatips.enums.Status;
import com.example.springdatajpatips.enums.UserLevel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the company_user database table.
 * 
 */
@Entity
@Table(name = "company_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyUser extends AuditEntity {

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Company
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="company_id")
	private Company company;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "territory_id")
	private Territory territory;

	@Column(name = "association_status", columnDefinition = "smallint")
	private Status associationStatus;

	@Enumerated
	@Column(name = "user_level", columnDefinition = "smallint")
	private UserLevel userLevel;
	
	public CompanyUser(Long id, Company company, User user) {
		this.setId(id);
		this.company = company;
		this.user = user;
	}

}