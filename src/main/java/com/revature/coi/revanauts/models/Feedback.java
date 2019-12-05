package com.revature.coi.revanauts.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FEEDBACK")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Skill skill;
	
	private Long associateId;	
	private long timeGiven;
	private String notes;
	private boolean isNudge;
	
	public Feedback() {
		super();
	}
	
	public Feedback(Long id) {
		super();
		this.id = id;
	}

	public Feedback(Long associateNudged, Skill skill, long timeGiven, String notes, boolean isNudge) {
		super();
		this.associateId = associateNudged;
		this.skill = skill;
		this.timeGiven = timeGiven;
		this.notes = notes;
		this.isNudge = isNudge;
	}

	public Feedback(Long id, Long associateNudged, Skill skill, long timeGiven, String notes,
			boolean isNudge) {
		super();
		this.id = id;
		this.associateId = associateNudged;
		this.skill = skill;
		this.timeGiven = timeGiven;
		this.notes = notes;
		this.isNudge = isNudge;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public long getTimeGiven() {
		return timeGiven;
	}

	public void setTimeGiven(long timeGiven) {
		this.timeGiven = timeGiven;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isNudge() {
		return isNudge;
	}

	public void setNudge(boolean isNudge) {
		this.isNudge = isNudge;
	}

	@Override
	public int hashCode() {
		return Objects.hash(associateId, id, isNudge, notes, skill, timeGiven);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Feedback))
			return false;
		Feedback other = (Feedback) obj;
		return Objects.equals(associateId, other.associateId) && Objects.equals(id, other.id)
				&& isNudge == other.isNudge && Objects.equals(notes, other.notes) && Objects.equals(skill, other.skill)
				&& Objects.equals(timeGiven, other.timeGiven);
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", associateNudged=" + associateId + ", skill=" + skill + ", timeGiven="
				+ timeGiven + ", notes=" + notes + ", isNudge=" + isNudge + "]";
	}
	

}
