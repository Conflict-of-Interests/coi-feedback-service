package com.revature.coi.revanauts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.coi.revanauts.models.Skill;
import com.revature.coi.revanauts.services.SkillService;

@CrossOrigin("*")
@RequestMapping("/skills")
@RestController("skillController")
public class SkillControllerImpl implements SkillController {

	private SkillService skillService;
	
	@Autowired
	public SkillControllerImpl(SkillService service) {
		this.skillService = service;
	}

	@Override
	@GetMapping(produces="application/json")
	public List<Skill> getAllSkills() {
		return skillService.getAllSkills();
	}

	@Override
	@GetMapping(value="/id/{id}", produces="application/json")
	public Skill getSkillById(@PathVariable long id) {
		return skillService.getSkillById(id);
	}

	@Override
	@GetMapping(value="/name/{name}", produces="application/json")
	public Skill getSkillByName(@PathVariable String name) {
		return skillService.getSkillByName(name);
	}

	@Override
	@PostMapping(produces="application/json", consumes="application/json")
	public Skill addNewSkill(@RequestBody Skill skill) {
		return skillService.addNewSkill(skill);
	}

	@Override
	@PutMapping(produces="application/json", consumes="application/json")
	public Skill updateSkill(@RequestBody Skill skill) {
		return skillService.updateSkill(skill);
	}
	
	
}
