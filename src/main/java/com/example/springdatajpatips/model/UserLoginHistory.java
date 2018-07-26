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
 * The persistent class for the user_login_history database table.
 * 
 */
@Entity
@Table(name = "user_login_history")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginHistory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "login_ts", columnDefinition="TIMESTAMP")
	private Timestamp loginTs;

	@Column(name = "logout_ts", columnDefinition="TIMESTAMP NULL")
	private Timestamp logoutTs;

}