package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ARMY_LIST")
public class ArmyList extends BaseEntity<ArmyList> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4779293053609328512L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="NAME", nullable = false, length = 64, updatable = true, insertable = true)
	private String listName;
	
	@Column(name="POINTS", nullable = false, updatable = true, insertable = true)
	private float totalPoints;
	
	@Column(name="DESC", nullable = false, length = 128, updatable = true, insertable = true)
	private String desc;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<UnitInfo> units = new HashSet<UnitInfo>();
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private AppUser appUser;
	
	public ArmyList() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, id, listName, totalPoints, units, appUser);
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
				&& Objects.equals(units, other.units)
				&& Objects.equals(appUser, other.appUser);
	}
}
