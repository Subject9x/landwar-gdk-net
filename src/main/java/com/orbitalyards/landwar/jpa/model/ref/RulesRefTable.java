package com.orbitalyards.landwar.jpa.model.ref;

import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="RULES_REF")
public class RulesRefTable extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6133090025098776865L;

	@Column(name="NAME", unique = false, nullable=false, updatable=true, insertable = true, length=64)
	private String name;
	
	@Column(name="DESC", unique = false, nullable=true, updatable=true, insertable = true, length=128)
	private String desc;
	
	public RulesRefTable() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, getId(), name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RulesRefTable other = (RulesRefTable) obj;
		return Objects.equals(desc, other.desc) && Objects.equals(getId(), other.getId()) && Objects.equals(name, other.name);
	}
}
