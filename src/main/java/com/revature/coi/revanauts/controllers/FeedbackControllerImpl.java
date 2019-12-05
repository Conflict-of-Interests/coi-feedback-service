package com.revature.coi.revanauts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.coi.revanauts.models.Feedback;
import com.revature.coi.revanauts.services.FeedbackService;

@CrossOrigin("*")
@RequestMapping("/feedback")
@RestController("feedbackController")
public class FeedbackControllerImpl implements FeedbackController {

	private FeedbackService feedbackService;
	
	@Autowired
	public FeedbackControllerImpl(FeedbackService service) {
		this.feedbackService = service;
	}
	
	@Override
	@GetMapping(produces="application/json")
	public List<Feedback> getAllFeedback() {
		return feedbackService.getAllFeedback();
	}

	@Override
	@GetMapping(value="/associates", produces="application/json", consumes="application/json")
	public List<Feedback> getAllFeedbackForAssociate(@RequestBody Feedback feedback) {
		return feedbackService.getAllFeedbackForAssociate(feedback);
	}

	@Override
	@GetMapping(value="/skills", produces="application/json", consumes="application/json")
	public List<Feedback> getAllFeedbackBySkill(@RequestBody Feedback feedback) {
		return feedbackService.getAllFeedbackBySkill(feedback);
	}

	@Override
	@GetMapping(value="/id/{id}", produces="application/json", consumes="application/json")
	public Feedback getFeedbackById(@RequestBody Feedback feedback) {
		return feedbackService.getFeedbackById(feedback);
	}

	@Override
	@PostMapping(produces="application/json", consumes="application/json")
	public Feedback addNewFeedback(@RequestBody Feedback feedback) {
		return feedbackService.addNewFeedback(feedback);
	}

	@Override
	@PutMapping(produces="application/json", consumes="application/json")
	public Feedback updateFeedback(@RequestBody Feedback feedback) {
		return feedbackService.updateFeedback(feedback);
	}

}
