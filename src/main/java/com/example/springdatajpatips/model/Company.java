package com.example.springdatajpatips.model;

import com.example.springdatajpatips.enums.Status;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * The persistent class for the company database table.
 *
 */
@Entity
@Table(name="company")
//@SQLDelete(sql = "UPDATE company SET status = 0 WHERE id = ? and version=?", check = ResultCheckStyle.COUNT)
//@Where(clause = "status = 1")
@Data
@EqualsAndHashCode
@ToString(exclude = { "companySubscriptions", "companyUsers"})
public class Company extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, length = 100)
	private String city;
	
	@Column(length = 100)
	private String state;

	@Column(name="company_domain", length = 100)
	private String companyDomain;

	@Column(name="company_url")
	private String companyUrl;

	@Column(name="group_id", nullable = false, length = 100)
	private String groupId;

	@Column(name="lock_ts", columnDefinition="TIMESTAMP NULL")
	private Timestamp lockTs;

	@Column(name="parent_company_name", length = 100, nullable = false)
	private String parentCompanyName;

	@Column(name="email_to_territory_admin",nullable = false)
	private Boolean sendEmailToTerritoryAdmin;

	@Enumerated
	@Column(columnDefinition = "smallint")
	private Status status;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="administration_territory_id")
	private Territory territory;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id")
	private Country country;

	//bi-directional many-to-one association to CompanySubscription
	@OneToMany(mappedBy="company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CompanySubscription> companySubscriptions;

	//bi-directional many-to-one association to CompanySubscription
	@OneToMany(mappedBy="company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CompanyUser> companyUsers;

}