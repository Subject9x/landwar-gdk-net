package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.orbitalyards.landwar.jpa.dao.impl.UnitArmyMap;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "armyList")
@Table(name = "ARMY_LIST")
public class ArmyList extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="UID", nullable = false, insertable=true, updatable = false, unique = true)
	private String uid;
	
	@Column(name="NAME", nullable = false, length = 64, updatable = true, insertable = true)
	private String listName;
	
	@Column(name="POINTS", nullable = false, updatable = true, insertable = true)
	private float totalPoints;
	
	@Column(name="DESC", nullable = false, length = 128, updatable = true, insertable = true)
	private String desc;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	private Set<UnitArmyMap> units = new HashSet<UnitArmyMap>();
	
	public ArmyList() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public float getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(float totalPoints) {
		this.totalPoints = totalPoints;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<UnitArmyMap> getUnits() {
		return units;
	}

	public void setUnits(Set<UnitArmyMap> units) {
		this.units = units;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, id, listName, totalPoints, uid, units);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArmyList other = (ArmyList) obj;
		return Objects.equals(desc, other.desc) && Objects.equals(id, other.id)
				&& Objects.equals(listName, other.listName)
				&& Float.floatToIntBits(totalPoints) == Float.floatToIntBits(other.totalPoints)
				&& Objects.equals(uid, other.uid) && Objects.equals(units, other.units);
	}
}
