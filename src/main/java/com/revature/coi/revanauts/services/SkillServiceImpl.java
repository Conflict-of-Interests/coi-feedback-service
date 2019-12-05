package com.revature.coi.revanauts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.coi.revanauts.exceptions.BadRequestException;
import com.revature.coi.revanauts.exceptions.ResourceCreationException;
import com.revature.coi.revanauts.exceptions.ResourceNotFoundException;
import com.revature.coi.revanauts.exceptions.ResourceUpdateException;
import com.revature.coi.revanauts.models.Skill;
import com.revature.coi.revanauts.repos.SkillRepository;

@Transactional
@Service("skillService")
public class SkillServiceImpl implements SkillService {
	
	private SkillRepository skillRepo;
	
	@Autowired
	public SkillServiceImpl(SkillRepository repo) {
		this.skillRepo = repo;
	}

	@Override
	public List<Skill> getAllSkills() {
		return skillRepo.findAll();
	}

	@Override
	public Skill getSkillById(Skill skill) {
		
		if(skill == null || skill.getId() <= 0) {
			throw new BadRequestException("Invalid value provided in request");
		}
		
		Optional<Skill> _skill = skillRepo.findById(skill.getId());
		
		if(!_skill.isPresent()) {
			throw new ResourceNotFoundException("No feedback found with provided id");
		}
		
		return _skill.get();
	
	}

	@Override
	public Skill getSkillByName(Skill skill) {
		
		if(skill == null || skill.getName().trim().equals("")) {
			throw new BadRequestException("Invalid value provided in request");
		}
		
		Skill retrievedSkill = skillRepo.findSkillByName(skill.getName());
		
		if(retrievedSkill == null) {
			throw new ResourceNotFoundException("No feedback found with provided id");
		}
		
		return retrievedSkill;
	}

	@Override
	public Skill addNewSkill(Skill skill) {
		
		Skill persistedSkill = skillRepo.save(skill);
		
		if(persistedSkill.getId() == 0) {
			throw new ResourceCreationException("There was a problem during resource creation");
		}
		
		return persistedSkill;
		
	}

	@Override
	public Skill updateSkill(Skill skill) {
		
		Skill updatedSkill = skillRepo.save(skill);
		
		if(updatedSkill.getId() == 0) {
			throw new ResourceUpdateException("There was a problem during resource update");
		}
		
		return updatedSkill;
		
	}

}
