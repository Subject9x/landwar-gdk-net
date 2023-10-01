package com.orbitalyards.landwar.jpa.model.ref;

import com.orbitalyards.landwar.jpa.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UNIT_TAGS")
public class UnitTag extends BaseModel {
	
	@Id
	@Column(name="ID", unique = true, nullable=false, updatable = false, insertable = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="TAG_ID", nullable = false, insertable = true, updatable = true)
	private int tag_id;
	
	@Column(name="TAG_MODULE", nullable=false, insertable = true, updatable = true)
	private int rulesId;
	
	public UnitTag() {}
	
	public UnitTag(Long id, int tag_id, int rulesId) {
		this.id = id;
		this.tag_id = tag_id;
		this.rulesId = rulesId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTagId() {
		return tag_id;
	}

	public void setTagId(int tag_id) {
		this.tag_id = tag_id;
	}

	public int getRulesId() {
		return rulesId;
	}

	public void setRulesId(int rulesId) {
		this.rulesId = rulesId;
	}
	
	
}
