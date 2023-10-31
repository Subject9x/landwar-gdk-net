package com.orbitalyards.landwar.jpa.model.map;

import java.math.BigInteger;
import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseEntity;
import com.orbitalyards.landwar.jpa.model.UnitInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UNIT_TAGS")
public class UnitTags extends BaseEntity<UnitTags>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5601700744409941847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	
	@JoinColumn(name = "unitinfo_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private UnitInfo unitInfoId;

	@Column(name = "unit_tag_id")
	private int unitTagId;
	
	@Column(name = "rules_id")
	private int rulesId;
	
	public UnitTags() {}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitTags other = (UnitTags) obj;
		return Objects.equals(id, other.id) 
				&& Objects.equals(unitInfoId, other.unitInfoId)
				&& Objects.equals(unitTagId, other.unitTagId)
				&& rulesId == other.rulesId;
	}
	
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public UnitInfo getUnitInfoId() {
		return unitInfoId;
	}

	public void setUnitInfoId(UnitInfo unitInfoId) {
		this.unitInfoId = unitInfoId;
	}

	public int getUnitTagId() {
		return unitTagId;
	}

	public void setUnitTagId(int unitTagId) {
		this.unitTagId = unitTagId;
	}
	
	public int getRulesId() {
		return rulesId;
	}

	public void setRulesId(int rulesId) {
		this.rulesId = rulesId;
	}
}
