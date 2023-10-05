package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "armyList")
@Table(name = "ARMY_LIST")
public class ArmyList extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4779293053609328512L;

	@Column(name="UID", nullable = false, insertable=true, updatable = false, unique = true)
	private String uid;
	
	@Column(name="NAME", nullable = false, length = 64, updatable = true, insertable = true)
	private String listName;
	
	@Column(name="POINTS", nullable = false, updatable = true, insertable = true)
	private float totalPoints;
	
	@Column(name="DESC", nullable = false, length = 128, updatable = true, insertable = true)
	private String desc;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<UnitInfo> units = new HashSet<UnitInfo>();
	
	public ArmyList() {}
	
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

	public Set<UnitInfo> getUnits() {
		return units;
	}

	public void setUnits(Set<UnitInfo> units) {
		this.units = units;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, getId(), listName, totalPoints, uid, units);
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
		return Objects.equals(desc, other.desc) && Objects.equals(getId(), other.getId())
				&& Objects.equals(listName, other.listName)
				&& Float.floatToIntBits(totalPoints) == Float.floatToIntBits(other.totalPoints)
				&& Objects.equals(uid, other.uid) && Objects.equals(units, other.units);
	}

	@Override
	public String toString() {
		return "ArmyList [id=" + getId() + ", uid=" + uid + ", listName=" + listName + ", totalPoints=" + totalPoints
				+ ", desc=" + desc + ", units=" + units + "]";
	}
}
