package com.orbitalyards.landwar.jpa.model;

import java.sql.Timestamp;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

/***
 * Handy basic model for all models to derive from, includes universal convenience columns and data.
 */
public class BaseModel {

	private static final Long serialVersionUID = 1L;
	
	@Column(name = "CREATE_DT_TM", insertable = true, updatable = true)
	private Timestamp createDate;
	
	@Version
	@Column(name = "UPDATE_DT_TM", insertable = true, updatable = true)
	private Timestamp updateDate;
	
	@PrePersist
	protected void onCreate() {
		createDate = new Timestamp(System.currentTimeMillis());
	}
	
	@PreUpdate
	protected void onUpdate() {
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
