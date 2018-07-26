package com.example.springdatajpatips.model;


import com.example.springdatajpatips.enums.Status;

import java.util.List;

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


/**
 * The persistent class for the private_group database table.
 * 
 */
@Entity
@Table(name="private_group")
@Data
@EqualsAndHashCode(callSuper=true)
public class PrivateGroup extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="territory_id")
	private Territory territory;

	@Enumerated
	@Column(columnDefinition = "smallint")
	private Status status;

	//bi-directional many-to-one association to PrivateGroupUser
	@OneToMany(mappedBy="privateGroup", cascade = CascadeType.ALL)
	private List<PrivateGroupUser> privateGroupUsers;

}