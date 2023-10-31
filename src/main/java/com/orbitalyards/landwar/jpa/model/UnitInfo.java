package com.orbitalyards.landwar.jpa.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.orbitalyards.landwar.jpa.model.map.UnitTags;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name="UNIT_INFO")
public class UnitInfo extends BaseEntity<UnitInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3406778538463459437L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "unitInfoId", fetch = FetchType.EAGER)
	private Set<UnitTags> tags = new HashSet<UnitTags>();
	
	@JoinColumn(name = "appuser_id", nullable = false, updatable = false, insertable = true)
	@ManyToOne(targetEntity = AppUser.class, fetch = FetchType.LAZY)
	private AppUser appUser;
	
	public UnitInfo() {}
	
	@PrePersist
	public void onPersist() {
		super.onCreate();
		
	}
	
	@PreUpdate
	public void onUpdate() {
		super.onUpdate();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<UnitTags> getTags() {
		return this.tags;
	}

	public void setTags(Set<UnitTags> tags) {
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
	
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
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
		return armor == other.armor && Objects.equals(desc, other.desc) 
				&& dmgMelee == other.dmgMelee
				&& dmgRange == other.dmgRange 
				&& evade == other.evade 
				&& Objects.equals(id, other.id)
				&& Objects.equals(imgUrl, other.imgUrl) 
				&& move == other.move
				&& Float.floatToIntBits(pointsCost) == Float.floatToIntBits(other.pointsCost) && range == other.range
				&& size == other.size && Objects.equals(tags, other.tags) && Objects.equals(unitName, other.unitName)
				&& Objects.equals(appUser, other.appUser);
	}

	@Override
	public String toString() {
		return "UnitInfo [id=" + id + ", unitName=" + unitName + ", pointsCost=" + pointsCost + ", size=" + size
				+ ", move=" + move + ", evade=" + evade + ", armor=" + armor + ", dmgMelee=" + dmgMelee + ", dmgRange="
				+ dmgRange + ", range=" + range + ", desc=" + desc + ", imgUrl=" + imgUrl
				+ ", tags=" + tags + ", appUser=" + appUser.getId() +"]";
	}
}
