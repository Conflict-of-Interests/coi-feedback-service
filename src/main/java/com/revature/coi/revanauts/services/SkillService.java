package com.revature.coi.revanauts.services;

import java.util.List;

import com.revature.coi.revanauts.models.Skill;

public interface SkillService {
	
	List<Skill> getAllSkills();
	Skill getSkillById(Skill skill);
	Skill getSkillByName(Skill skill);
	Skill addNewSkill(Skill skill);
	Skill updateSkill(Skill skill);

}
