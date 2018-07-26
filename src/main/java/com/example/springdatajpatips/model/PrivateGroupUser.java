package com.example.springdatajpatips.model;


import com.example.springdatajpatips.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * The persistent class for the private_group_user database table.
 * 
 */
@Entity
@Table(name = "private_group_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class PrivateGroupUser extends AuditEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to PrivateGroup
	@ManyToOne
	@JoinColumn(name="private_group_id")
	private PrivateGroup privateGroup;

	//it shd be many to many relationship as per the requirement

	@Enumerated
	@Column(columnDefinition = "smallint")
	private Status status;

}