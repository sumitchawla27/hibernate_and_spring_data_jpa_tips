package com.example.springdatajpatips.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AuditEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "created_by")
	@CreatedBy
	private Long createdBy;
	
	@Column(name = "created_ts", columnDefinition="TIMESTAMP", updatable = false)
	@CreatedDate
	private Timestamp createdTs;

	@Column(name = "updated_by")
	@LastModifiedBy
	private Long updatedBy;

	@Column(name = "updated_ts", columnDefinition="TIMESTAMP NULL")
	@LastModifiedDate
	private Timestamp updatedTs;


}
