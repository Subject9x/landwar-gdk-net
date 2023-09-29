package com.orbitalyards.landwar.mvc.model.data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/***
 * Convenience model class for rules tags.
 */
public class UnitTag {

	@JsonProperty(defaultValue = "0")
	private int id;
	
	@JsonProperty(defaultValue = "0")
	private int rulesId;
	
	
	public UnitTag() {}


	@JsonGetter(value = "tagId")
	public int getId() {
		return id;
	}

	@JsonSetter(value = "tagId")
	public void setId(int id) {
		this.id = id;
	}

	@JsonGetter(value = "rulesId")
	public int getRulesId() {
		return rulesId;
	}

	@JsonSetter(value = "rulesId")
	public void setRulesId(int rulesId) {
		this.rulesId = rulesId;
	}
}
