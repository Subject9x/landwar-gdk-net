package com.orbitalyards.landwar.mvc.model;

import java.util.List;

import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;

/***
 * For extremely simple inter-page post/get.
 * @author roohr
 */
public class UnitBasicResponse {

	private List<UnitInfoEntry> units;
	
	public List<UnitInfoEntry> getUnits() {
		return units;
	}

	public void setUnits(List<UnitInfoEntry> units) {
		this.units = units;
	}
}
