package com.orbitalyards.landwar.jpa.model;

import java.util.ArrayList;
import java.util.List;

import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;

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

/***
 * JPA-layer persistence model, this is used for binding data to the database, 
 * or retrieving.
 * 
 * Use {@linkplain UnitInfoEntryDTO} for conversion operations.
 * 
 * @author Roohr
 */
@Entity
@Table(name="UNIT_INFO", schema="")
public class UnitInfo extends BaseModel{
	
	@Id
	@Column(name="id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="uid", nullable=false, length=512)
	private String uid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", unique = true, nullable = false)
	private User user;
	
	@Column(name="name", nullable=false, length=64)
	private String unitName;
	
	@Column(name="points", nullable=false)
	private float pointsCost;
	
	@Column(name="size", nullable=false)
	private int size;
	
	@Column(name="move", nullable=false)
	private int move;
	
	@Column(name="evade", nullable=false)
	private int evade;
	
	@Column(name="armor", nullable=false)
	private int armor;
	
	@Column(name="dmgMelee", nullable=false)
	private int dmgMelee;
	
	@Column(name="dmgRange", nullable=false)
	private int dmgRange;
	
	@Column(name="range", nullable=false)
	private int range;
	
	@OneToMany(mappedBy= "tagId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	private List<UnitTag> tags = new ArrayList<UnitTag>();
	
	@Column(name="desc", nullable=false, length=256)
	private String desc;
	
	@Column(name="imageUrl", nullable=false, length=256)
	private String imgUrl;
	
	
	public UnitInfo() {}
	
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
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public float getPointsCost() {
		return pointsCost;
	}

	public void setPointsCost(float pointsCost) {
		this.pointsCost = pointsCost;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public int getEvade() {
		return evade;
	}

	public void setEvade(int evade) {
		this.evade = evade;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getDmgMelee() {
		return dmgMelee;
	}

	public void setDmgMelee(int dmgMelee) {
		this.dmgMelee = dmgMelee;
	}

	public int getDmgRange() {
		return dmgRange;
	}

	public void setDmgRange(int dmgRange) {
		this.dmgRange = dmgRange;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public List<UnitTag> getTags() {
		List<UnitTag> out = new ArrayList<UnitTag>();
		for(UnitTag t : this.tags) {
			out.add(t);
		}
		return this.tags;
	}

	public void setTags(List<UnitTag> tags) {
		this.tags = tags;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "UnitInfoModel [unitName=" + unitName + ", pointsCost=" + pointsCost + ", size=" + size + ", move="
				+ move + ", evade=" + evade + ", armor=" + armor + ", dmgMelee=" + dmgMelee + ", dmgRange=" + dmgRange
				+ ", range=" + range + ", tags=" + tags + ", desc=" + desc + ", imgUrl=" + imgUrl + "]";
	}
}
