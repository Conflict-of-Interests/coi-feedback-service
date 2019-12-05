package com.revature.coi.revanauts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
