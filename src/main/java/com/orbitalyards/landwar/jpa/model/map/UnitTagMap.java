package com.orbitalyards.landwar.jpa.model.map;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "unitTagMap")
@Table(name="UNIT_TAG_MAP")
public class UnitTagMap{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "tag_id", unique = false, nullable = false)
	private Long tagId;
	
	public UnitTagMap() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tagId, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitTagMap other = (UnitTagMap) obj;
		return Objects.equals(tagId, other.tagId)
				&& Objects.equals(id, other.id);
	}
}
