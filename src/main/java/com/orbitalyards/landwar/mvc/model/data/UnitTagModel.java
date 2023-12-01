package com.orbitalyards.landwar.mvc.model.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Convenience model class for rules tags.
 */
@JsonAutoDetect
public class UnitTagModel {

	@JsonProperty(defaultValue = "0", required = true)
	private int id;
	
	@JsonProperty(defaultValue = "0")
	private int rulesId;
	
	@JsonProperty(defaultValue = "", required = false)
	private String name;
	
	public UnitTagModel() {}
	
	@JsonIgnore
	public UnitTagModel(int tagId, int rulesId) {
		this.id = tagId;
		this.rulesId = rulesId;
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRulesId() {
		return rulesId;
	}
	
	public void setRulesId(int rulesId) {
		this.rulesId = rulesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
