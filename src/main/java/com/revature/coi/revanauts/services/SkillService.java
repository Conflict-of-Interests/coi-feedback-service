package com.revature.coi.revanauts.services;

import java.util.List;

import com.revature.coi.revanauts.models.Skill;

public interface SkillService {
	
	List<Skill> getAllSkills();
	Skill getSkillById(long id);
	Skill getSkillByName(String skill);
	Skill addNewSkill(Skill skill);
	Skill updateSkill(Skill skill);

}
