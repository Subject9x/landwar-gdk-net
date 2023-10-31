package com.orbitalyards.landwar.jpa.model.map;

import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "unitArmyMap")
@Table(name="UNIT_ARMY_MAP")
public class UnitArmyMap extends BaseEntity<UnitArmyMap>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "unit_id", unique = false)
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
		return Objects.hash(unitId, id);
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
		return Objects.equals(unitId, other.unitId) && Objects.equals(id, other.id);
	}
}
