package com.orbitalyards.landwar.mvc.model.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * ArmyLists are discrete collections of {@linkplain UnitInfoEntry}
 * 
 * This is Mutable and not the persistence copy, DTO/DAO will bind data to DB.
 * @author Roohr
 */
@JsonRootName(value = "armyList")
public final class ArmyList {

	@JsonProperty(defaultValue = "-1")
	private String uid;
	
	@JsonProperty(defaultValue = "-1")
	private String userId;
	
	@JsonProperty(defaultValue = "[]",  required = true)
	private List<UnitInfoEntry> units;
	
	@JsonProperty(defaultValue = "", required = true)
	private String listName;
	
	@JsonProperty(defaultValue = "0", required = true)
	private float totalPoints;
	
	@JsonProperty(defaultValue = "", required = true)
	private String desc;
	
	public ArmyList() {}

	@JsonGetter(value="uid")
	public String getUid() {
		return uid;
	}
	
	@JsonSetter(value="uid")
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	@JsonGetter(value="user")
	public String getUserId() {
		return userId;
	}

	@JsonSetter(value="user")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UnitInfoEntry> getUnits() {
		List<UnitInfoEntry> out = new ArrayList<UnitInfoEntry>();
		units.stream().forEach( (UnitInfoEntry e) -> {
			out.add((UnitInfoEntry)e.clone());
		});
		
		out.addAll(this.units);
		return out;
	}

	public void setUnits(List<UnitInfoEntry> units) {
		this.units = units;
	}

	@JsonGetter("name")
	public String getListName() {
		return listName;
	}

	@JsonSetter("name")
	public void setListName(String listName) {
		this.listName = listName;
	}

	@JsonGetter("points")
	public float getTotalPoints() {
		return totalPoints;
	}

	@JsonSetter("points")
	public void setTotalPoints(float totalPoints) {
		this.totalPoints = totalPoints;
	}

	@JsonGetter("desc")
	public String getDesc() {
		return desc;
	}

	@JsonSetter("desc")
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public ArmyList clone() {
		ArmyList newList = new ArmyList();
		
		newList.setDesc(new String(getDesc()));
		newList.setListName(new String(getListName()));
		newList.setTotalPoints(getTotalPoints());
		newList.setUnits(getUnits());
		
		return newList;
	}
}
