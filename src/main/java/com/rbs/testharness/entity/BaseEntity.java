package com.rbs.testharness.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class BaseEntity {
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_TS")
	private LocalDateTime createdTs;
	@Column(name = "LAST_UPD_BY")
	private String updatedBy;
	@Column(name = "LAST_UPD_TS")
	private LocalDateTime updatedTs;
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(LocalDateTime updatedTs) {
		this.updatedTs = updatedTs;
	}
}
