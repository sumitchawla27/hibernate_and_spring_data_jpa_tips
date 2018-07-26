package com.example.springdatajpatips.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * The persistent class for the territory database table.
 * 
 */
@Entity
@Table(name="territory")
@Data
@EqualsAndHashCode(callSuper=true)
public class Territory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 5)
	private String code;

	@Column(nullable = false)
	private Boolean onTop = false;

}