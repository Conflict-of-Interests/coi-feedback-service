package com.revature.coi.revanauts.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SKILL")
public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String improvementTips;
	
	public Skill() {
		super();
	}

	public Skill(Long id, String name, String description, String improvementTips) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.improvementTips = improvementTips;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImprovementTips() {
		return improvementTips;
	}

	public void setImprovementTips(String improvementTips) {
		this.improvementTips = improvementTips;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", description=" + description + ", improvementTips="
				+ improvementTips + "]";
	}
	
}
