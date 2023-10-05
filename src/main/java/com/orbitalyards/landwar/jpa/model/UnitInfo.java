package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.orbitalyards.landwar.jpa.model.ref.UnitTag;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/***
 * JPA-layer persistence model, this is used for binding data to the database, 
 * or retrieving.
 * 
 * Use {@linkplain UnitInfoEntryDTO} for conversion operations.
 * 
 * @author Roohr
 */
@Entity(name = "unitInfo")
@Table(name="UNIT_INFO")
public class UnitInfo extends BaseModel{


//	@Column(nullable=false, updatable = false, insertable = true, length=512)
//	private String uid;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3406778538463459437L;

	@Column(nullable=false, updatable = true, insertable = true, length=64)
	private String unitName;
	
	@Column(nullable=false, updatable = true, insertable = true)
	private float pointsCost;
	
	@Column(nullable=false, updatable = true, insertable = true)
	private int size;
	
	@Column(nullable=false, updatable = true, insertable = true)
	private int move;
	
	@Column( nullable=false, updatable = true, insertable = true)
	private int evade;
	
	@Column(nullable=false, updatable = true, insertable = true)
	private int armor;
	
	@Column(name="DMG_MEL", nullable=false, updatable = true, insertable = true)
	private int dmgMelee;
	
	@Column(name="DMG_RNG", nullable=false, updatable = true, insertable = true)
	private int dmgRange;
	
	@Column(nullable=false, updatable = true, insertable = true)
	private int range;
	
	@Column(nullable=true, updatable = true, insertable = true, length=256)
	private String desc;
	
	@Column(name="IMG_URL", nullable=true, updatable = true, insertable = true, length=256)
	private String imgUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	private User appUser;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Set<UnitTag> tags = new HashSet<UnitTag>();
	
	public UnitInfo() {}
	
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

	public Set<UnitTag> getTags() {
		return this.tags;
	}

	public void setTags(Set<UnitTag> tags) {
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
	
	public User getAppUser() {
		return appUser;
	}

	public void setAppUser(User appUser) {
		this.appUser = appUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(armor, desc, dmgMelee, dmgRange, evade, getId(), imgUrl, move, pointsCost, range, size, tags,
				 unitName, appUser);
		//uid,
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitInfo other = (UnitInfo) obj;
		return armor == other.armor && Objects.equals(desc, other.desc) && dmgMelee == other.dmgMelee
				&& dmgRange == other.dmgRange && evade == other.evade && Objects.equals(getId(), other.getId())
				&& Objects.equals(imgUrl, other.imgUrl) && move == other.move
				&& Float.floatToIntBits(pointsCost) == Float.floatToIntBits(other.pointsCost) && range == other.range
				&& size == other.size && Objects.equals(tags, other.tags) && Objects.equals(unitName, other.unitName)
				&& Objects.equals(appUser, other.appUser);
		//&& Objects.equals(uid, other.uid)
	}

	@Override
	public String toString() {
		return "UnitInfo [id=" + getId() + ", unitName=" + unitName + ", pointsCost=" + pointsCost + ", size=" + size
				+ ", move=" + move + ", evade=" + evade + ", armor=" + armor + ", dmgMelee=" + dmgMelee + ", dmgRange="
				+ dmgRange + ", range=" + range + ", desc=" + desc + ", imgUrl=" + imgUrl + ", appUser=" + appUser
				+ ", tags=" + tags + "]";
	}
}
