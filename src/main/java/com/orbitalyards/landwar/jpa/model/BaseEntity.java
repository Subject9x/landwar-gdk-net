package com.orbitalyards.landwar.jpa.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

/***
 * Handy basic model for all models to derive from, includes universal convenience columns and data.
 */
@MappedSuperclass
public abstract class BaseEntity<Id extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CREATE_TM", insertable = true, updatable = false)
	private Timestamp createDate;
	
	@Version
	@Column(name = "UPDATE_TM")
	private Timestamp updateDate;
	
	@PrePersist
	protected void onCreate() {
		createDate = new Timestamp(System.currentTimeMillis());
	}
	
	@PreUpdate
	public void onUpdate() {
		updateDate = new Timestamp(System.currentTimeMillis());
	}
	
	public Optional<Timestamp> getCreateDate() {
		return Optional.ofNullable(createDate);
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Optional<Timestamp> getUpdateDate() {
		return Optional.ofNullable(updateDate);
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
}
