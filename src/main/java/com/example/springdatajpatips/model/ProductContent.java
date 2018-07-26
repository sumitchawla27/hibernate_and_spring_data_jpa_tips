package com.example.springdatajpatips.model;


import com.example.springdatajpatips.enums.ProductContentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * The persistent class for the product_content database table.
 * 
 */
@Entity
@Table(name = "product_content")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductContent extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;
	
	@Lob
	private String description;
	
	@Enumerated
	@Column(columnDefinition = "smallint")
	private ProductContentType type;

	@Column(name="seq_no", columnDefinition = "smallint")
	private int seqNo;


}