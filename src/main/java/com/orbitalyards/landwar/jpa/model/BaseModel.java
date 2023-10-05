package com.orbitalyards.landwar.jpa.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

/***
 * Handy basic model for all models to derive from, includes universal convenience columns and data.
 */
@MappedSuperclass
public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CREATE_DT_TM", insertable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Timestamp createDate;
	
	@Version
	@Column(name = "UPDATE_DT_TM", insertable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Timestamp updateDate;
	
	@PrePersist
	protected void onCreate() {
		this.createDate =new Timestamp(System.currentTimeMillis()); 
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updateDate = new Timestamp(System.currentTimeMillis());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
