package com.orbitalyards.landwar.jpa.model;

import java.util.List;

import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ARMY_LIST", schema="")
public class ArmyList extends BaseModel {

	@Id
	@Column(name ="id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="uid", nullable = false, insertable=true, updatable = false)
	private String uid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", unique = true, nullable = false)
	private User user;
	
	@Column(name="name", nullable = false, length = 64)
	private String listName;
	
	@Column(name="points", nullable = false)
	private float totalPoints;
	
	@Column(name="desc", nullable = false, length = 128)
	private String desc;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	private List<UnitInfoEntry> units;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<UnitInfoEntry> getUnits() {
		return units;
	}

	public void setUnits(List<UnitInfoEntry> units) {
		this.units = units;
	}
}
