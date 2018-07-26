package com.example.springdatajpatips.model;


import com.example.springdatajpatips.enums.Status;
import com.example.springdatajpatips.enums.UserLevel;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AuditEntity {

	private static final long serialVersionUID = 1L;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	// uni-directional many-to-one association to Territory
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "territory_id")
	private Territory territory;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	private String company;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(length = 20)
	private String telephone;

	@Enumerated
	@Column(columnDefinition = "smallint")
	private UserLevel level;

	@Column(name = "idam_user_group", length = 3)
	private String idamUserGroup;

	@Column(name = "exists_in_idam")
	private Boolean existsInIdam;

	@Column(name = "sam_account_no", length = 100)
	private String samAccountNo;

	@Column(name = "generic_tnc_accepted_ts", columnDefinition = "TIMESTAMP NULL")
	private Timestamp genericTncAcceptedTs;

	@Column(name = "registration_ts", columnDefinition = "TIMESTAMP NULL")
	private Timestamp registrationTs;

	@Column(name = "activation_sent_ts", columnDefinition = "TIMESTAMP NULL")
	private Timestamp activationSentTs;

	@Column(name = "activation_ts", columnDefinition = "TIMESTAMP NULL")
	private Timestamp activationTs;

	@Column(name = "activation_expiry_ts", columnDefinition = "TIMESTAMP NULL")
	private Timestamp activationExpiryTs;

	@Column(name = "forgot_pwd_sent_ts", columnDefinition = "TIMESTAMP NULL")
	private Timestamp forgotPwdSentTs;

	@Column(name = "reset_pwd_ts", columnDefinition = "TIMESTAMP NULL")
	private Timestamp resetPwdTs;

	private String guid;

	//it is being used to track whether user is soft deleted from system or not
	@Enumerated
	@Column(columnDefinition = "smallint")
	private Status status;



	
}