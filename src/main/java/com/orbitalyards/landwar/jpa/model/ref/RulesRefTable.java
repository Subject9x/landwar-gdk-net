package com.orbitalyards.landwar.jpa.model.ref;

import com.orbitalyards.landwar.jpa.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="RULES_REF")
public class RulesRefTable extends BaseModel {

	@Id
	@Column(name="ID", unique = true, nullable=false, updatable=true, insertable = true)
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
	
	
	
	
}
