package com.orbitalyards.landwar.jpa.dao.impl;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "unitArmyMap")
@Table(name = "UNIT_ARMY_MAP")
public class UnitArmyMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "unit_id", unique = false, nullable = false, insertable = true, updatable = true)
	private Long unitId;
	
	public UnitArmyMap() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, unitId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitArmyMap other = (UnitArmyMap) obj;
		return Objects.equals(id, other.id) && Objects.equals(unitId, other.unitId);
	}
	
}
