package com.revature.coi.revanauts.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.coi.revanauts.models.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	
	Skill findSkillByName(String name);

}
