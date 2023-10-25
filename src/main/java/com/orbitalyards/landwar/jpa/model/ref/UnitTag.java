package com.orbitalyards.landwar.jpa.model.ref;

import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="UNIT_TAGS")
public class UnitTag extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2411294600703603034L;

	@Column(name="TAG_ID", nullable = false, insertable = true, updatable = true)
	private int tagId;
	
	@Column(name="TAG_MODULE", nullable=false, insertable = true, updatable = true)
	private int rulesId;
	
	public UnitTag() {}


	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getRulesId() {
		return rulesId;
	}

	public void setRulesId(int rulesId) {
		this.rulesId = rulesId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(rulesId, tagId, getId(), getCreateDate(), getUpdateDate());
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitTag other = (UnitTag) obj;
		return rulesId == other.rulesId && tagId == other.tagId && getId() == other.getId() && getCreateDate().equals(other.getCreateDate());
	}
}
