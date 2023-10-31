package com.orbitalyards.landwar.jpa.model.ref;

import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UNIT_TAGS_REF")
public class UnitTagRef extends BaseEntity<UnitTagRef> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2411294600703603034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TAG_ID", nullable = false, insertable = true, updatable = true)
	private int tagId;
	
	@Column(name="TAG_MODULE", nullable=false, insertable = true, updatable = true)
	private int rulesId;
	
	
	public UnitTagRef() {}
	
	public UnitTagRef(int tagId, int rulesId) {
		this.tagId = tagId;
		this.rulesId = rulesId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, rulesId, tagId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitTagRef other = (UnitTagRef) obj;
		return Objects.equals(id, other.id) && rulesId == other.rulesId && tagId == other.tagId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	
}
