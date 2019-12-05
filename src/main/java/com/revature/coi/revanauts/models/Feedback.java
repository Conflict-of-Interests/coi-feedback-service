package com.revature.coi.revanauts.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FEEDBACK")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long associateNudged;
	private Skill skill;
	private LocalDateTime timeGiven;
	private String notes;
	private boolean isNudge;
	
	public Feedback() {
		super();
	}

	public Feedback(Long associateNudged, Skill skill, LocalDateTime timeGiven, String notes, boolean isNudge) {
		super();
		this.associateNudged = associateNudged;
		this.skill = skill;
		this.timeGiven = timeGiven;
		this.notes = notes;
		this.isNudge = isNudge;
	}

	public Feedback(Long id, Long associateNudged, Skill skill, LocalDateTime timeGiven, String notes,
			boolean isNudge) {
		super();
		this.id = id;
		this.associateNudged = associateNudged;
		this.skill = skill;
		this.timeGiven = timeGiven;
		this.notes = notes;
		this.isNudge = isNudge;
	}

	@Override
	public int hashCode() {
		return Objects.hash(associateNudged, id, isNudge, notes, skill, timeGiven);
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
		return Objects.equals(associateNudged, other.associateNudged) && Objects.equals(id, other.id)
				&& isNudge == other.isNudge && Objects.equals(notes, other.notes) && Objects.equals(skill, other.skill)
				&& Objects.equals(timeGiven, other.timeGiven);
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", associateNudged=" + associateNudged + ", skill=" + skill + ", timeGiven="
				+ timeGiven + ", notes=" + notes + ", isNudge=" + isNudge + "]";
	}
	

}
