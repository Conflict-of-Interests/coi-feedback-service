package com.revature.coi.revanauts.controllers;

import java.util.List;

import com.revature.coi.revanauts.models.Skill;

public interface SkillController {
	
	List<Skill> getAllSkills();
	Skill getSkillById(Skill skill);
	Skill getSkillByName(Skill skill);
	Skill addNewSkill(Skill skill);
	Skill updateSkill(Skill skill);

}
