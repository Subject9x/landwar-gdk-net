package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.orbitalyards.landwar.jpa.model.map.UnitTagMap;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false, updatable = false, insertable = true, length=512)
	private String uid;
	
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<UnitTagMap> tags = new HashSet<UnitTagMap>();
	
	
//	@ManyToMany()
//	private List<ArmyList> armyLists = new ArrayList<ArmyList>();
	
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

	public Set<UnitTagMap> getTags() {
		return this.tags;
	}

	public void setTags(Set<UnitTagMap> tags) {
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
	public int hashCode() {
		return Objects.hash(armor, desc, dmgMelee, dmgRange, evade, id, imgUrl, move, pointsCost, range, size, tags,
				uid, unitName);
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
				&& dmgRange == other.dmgRange && evade == other.evade && Objects.equals(id, other.id)
				&& Objects.equals(imgUrl, other.imgUrl) && move == other.move
				&& Float.floatToIntBits(pointsCost) == Float.floatToIntBits(other.pointsCost) && range == other.range
				&& size == other.size && Objects.equals(tags, other.tags) && Objects.equals(uid, other.uid)
				&& Objects.equals(unitName, other.unitName);
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

//	public List<ArmyList> getArmyLists() {
//		return armyLists;
//	}
//
//	public void setArmyLists(List<ArmyList> armyLists) {
//		this.armyLists = armyLists;
//	}
	
	
}
