package com.orbitalyards.landwar.mvc.model.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

/***
 * Unit Info entry is the atomic data class for all LANDWAR units.
 * contains all vital game info for a Unit.
 * 
 * This is Mutable and not the persistence copy, DTO/DAO will bind data to DB.
 * 
 * @author Roohr
 */
@JsonRootName(value = "unitInfo")
public final class UnitInfoEntry {

	//we propagate this for handling updates to existing units.
	@JsonProperty(defaultValue = "0", required = true)
	private long dbId;
	
	@JsonProperty(defaultValue = "", required = true)
	private String unitName;

	@JsonProperty(defaultValue = "0", required = true)
	private float pointsCost;

	@JsonProperty(defaultValue = "0", required = true)
	private int size;

	@JsonProperty(defaultValue = "0", required = true)
	private int move;

	@JsonProperty(defaultValue = "0", required = true)
	private int evade;

	@JsonProperty(defaultValue = "0", required = true)
	private int armor;

	@JsonProperty(defaultValue = "0", required = true)
	private int dmgMelee;

	@JsonProperty(defaultValue = "0", required = true)
	private int dmgRange;

	@JsonProperty(defaultValue = "0", required = true)
	private int range;
	
	@JsonProperty(defaultValue = "[]", required = false)
	private List<UnitTagModel> tags;

	@JsonProperty(defaultValue = "", required = true)
	private String desc;

	@JsonProperty(defaultValue = "0", required = true)
	private String imgUrl;
	
	
	public UnitInfoEntry(){
		this.unitName = "";
		this.desc = "";
		this.imgUrl = "";
	}
	
	@JsonGetter("dbId")
	public long getId() {
		return dbId;
	}
	
	@JsonSetter("dbId")
	public void setId(long dbId) {
		this.dbId = dbId;
	}

	@JsonGetter("name")
	public String getUnitName() {
		return unitName;
	}

	@JsonSetter("name")
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@JsonGetter("points")
	public float getPointsCost() {
		return pointsCost;
	}

	@JsonSetter("points")
	public void setPointsCost(float pointsCost) {
		this.pointsCost = pointsCost;
	}

	@JsonGetter("size")
	public int getSize() {
		return size;
	}

	@JsonSetter("size")
	public void setSize(int size) {
		this.size = size;
	}
	
	@JsonGetter("move")
	public int getMove() {
		return move;
	}

	@JsonSetter("move")
	public void setMove(int move) {
		this.move = move;
	}

	@JsonGetter("evade")
	public int getEvade() {
		return evade;
	}

	@JsonSetter("evade")
	public void setEvade(int evade) {
		this.evade = evade;
	}

	@JsonGetter("armor")
	public int getArmor() {
		return armor;
	}

	@JsonSetter("armor")
	public void setArmor(int armor) {
		this.armor = armor;
	}

	@JsonGetter("DMGM")
	public int getDmgMelee() {
		return dmgMelee;
	}

	@JsonSetter("DMGM")
	public void setDmgMelee(int dmgMelee) {
		this.dmgMelee = dmgMelee;
	}

	@JsonGetter("DMGR")
	public int getDmgRange() {
		return dmgRange;
	}

	@JsonSetter("DMGR")
	public void setDmgRange(int dmgRange) {
		this.dmgRange = dmgRange;
	}

	@JsonGetter("range")
	public int getRange() {
		return range;
	}

	@JsonSetter("range")
	public void setRange(int range) {
		this.range = range;
	}

	public void setTags(List<UnitTagModel> newTags) {
		this.tags = newTags;
	}
	
	public List<UnitTagModel> getTags() {
		//defensive copy
		List<UnitTagModel> out = new ArrayList<UnitTagModel>();
		tags.stream().forEach((UnitTagModel t) -> {
			out.add(t);
		});
		return out;
	}

	@JsonGetter("desc")
	public String getDesc() {
		return desc;
	}

	@JsonSetter("desc")
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@JsonGetter("img")
	public String getImgUrl() {
		return imgUrl;
	}

	@JsonSetter("img")
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Override
	/***
	 * 
	 */
	public Object clone() {
		UnitInfoEntry clone = new UnitInfoEntry();
		
		clone.setArmor(getArmor());
		clone.setDesc(new String(getDesc()));
		clone.setDmgMelee(getDmgMelee());
		clone.setDmgRange(getDmgRange());
		clone.setEvade(getEvade());
		clone.setImgUrl(new String(getImgUrl()));
		clone.setMove(getMove());
		clone.setPointsCost(getPointsCost());
		clone.setRange(getRange());
		clone.setSize(getSize());
		clone.setTags(getTags());
		clone.setUnitName(new String(getUnitName()));
		clone.setId(-1);	//id is databaseId
		
		return clone;
	}


	@Override
	public String toString() {
		return "UnitInfoEntry [dbId=" + dbId + ", unitName=" + unitName + ", pointsCost=" + pointsCost + ", size=" + size + ", move="
				+ move + ", evade=" + evade + ", armor=" + armor + ", dmgMelee=" + dmgMelee + ", dmgRange=" + dmgRange
				+ ", range=" + range + ", desc=" + desc + ", imgUrl=" + imgUrl + ", tags={" + tags.stream().toArray() +"}]";
	}
}
