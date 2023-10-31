package com.orbitalyards.landwar.jpa.model.ref;

import java.util.Objects;

import com.orbitalyards.landwar.jpa.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="RULES_REF")
public class RulesRefTable extends BaseEntity<RulesRefTable> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6133090025098776865L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME", unique = false, nullable=false, updatable=true, insertable = true, length=64)
	private String name;
	
	@Column(name="DESC", unique = false, nullable=true, updatable=true, insertable = true, length=128)
	private String desc;
	
	public RulesRefTable() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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
		return Objects.hash(desc, id, name);
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
		return Objects.equals(desc, other.desc) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
