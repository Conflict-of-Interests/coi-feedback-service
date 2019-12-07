package com.revature.coi.revanauts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
		
		List<Skill> skills = skillRepo.findAll();
		
		if(skills.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No resources found.");
		}
		
		return skills;
		
	}

	@Override
	public Skill getSkillById(long skillId) {
		
		if(skillId <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value provided in request");
		}
		
		Optional<Skill> _skill = skillRepo.findById(skillId);
		
		if(!_skill.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No skill found with provided id");
		}
		
		return _skill.get();
	
	}

	@Override
	public Skill getSkillByName(String name) {
		
		if(name == null || name.trim().equals("")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value provided in request");
		}
		
		Skill retrievedSkill = skillRepo.findSkillByName(name);
		
		if(retrievedSkill == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No skill found with provided id");
		}
		
		return retrievedSkill;
	}

	@Override
	public Skill addNewSkill(Skill skill) {
		
		if (!isSkillValid(skill)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid skill object provided in request");
		}
		
		Skill shouldNotExist = skillRepo.findSkillByName(skill.getName());
		
		if(shouldNotExist != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "There is already a skill in the data source with that name.");
		}
		
		Skill persistedSkill = skillRepo.save(skill);
		
		if(persistedSkill.getId() == 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "There was a problem during resource creation");
		}
		
		return persistedSkill;
		
	}

	@Override
	public Skill updateSkill(Skill skill) {

		if (skill == null || skill.getId() <= 0 || !isSkillValid(skill)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value provided in request");
		} else if (!skillRepo.findById(skill.getId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No skill found with provided id");
		} else if (skillRepo.findSkillByName(skill.getName()) == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "There is already a skill in the data source with that name.");
		}
		
		Skill updatedSkill = skillRepo.save(skill);
		
		if(updatedSkill.getId() == 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "There was a problem during resource update");
		}
		
		return updatedSkill;
		
	}
	
	@Override
	public void deleteSkillById(long id) {
		
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value provided in request");
		}
		
		skillRepo.deleteById(id);
		
	}
	
	private boolean isSkillValid(Skill skill) {
		if (skill == null) return false;
		if (skill.getName() == null || skill.getName().trim().equals("")) return false;
		if (skill.getDescription() == null || skill.getDescription().trim().equals("")) return false;
		if (skill.getImprovementTips() == null || skill.getImprovementTips().trim().equals("")) return false;
		return true;
	}

}
