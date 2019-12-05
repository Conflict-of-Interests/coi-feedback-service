package com.revature.coi.revanauts.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.coi.revanauts.models.Feedback;
import com.revature.coi.revanauts.models.Skill;
import com.revature.coi.revanauts.repos.FeedbackRepository;

@Transactional
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

	private FeedbackRepository feedbackRepo;
	private SkillService skillService;
	
	@Autowired
	public FeedbackServiceImpl(FeedbackRepository repo) {
		this.feedbackRepo = repo;
	}
	
	@Autowired
	public void setSkillService(SkillService service) {
		this.skillService = service;
	}

	@Override
	public List<Feedback> getAllFeedback() {
		
		List<Feedback> feedback = feedbackRepo.findAll();
		
		if(feedback.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No feedback found for provided associate id");
		}
		
		return feedback;
	}

	@Override
	public List<Feedback> getAllFeedbackForAssociate(Feedback feedback) {
		
		long associateId = feedback.getAssociateId();
		
		if(associateId <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid associate id provided");
		}
		
		return feedbackRepo.findFeedbackByAssociateId(associateId);
	}

	@Override
	public List<Feedback> getAllFeedbackBySkill(long skillId) {
		
		if (skillId <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value provided in request");
		}
		
		Skill skill = skillService.getSkillById(skillId);
		List<Feedback> associateFeedback = feedbackRepo.findFeedbackBySkill(skill);
		if(associateFeedback.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No feedback found for provided associate id");
		}
		
	}

	@Override
	public Feedback getFeedbackById(Feedback feedback) {
		
		if(feedback == null || feedback.getId() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value provided in request");
		}
		
		Optional<Feedback> _feedback = feedbackRepo.findById(feedback.getId());
		
		if(!_feedback.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No feedback found with provided id");
		}
		
		return _feedback.get();
		
	}

	@Override
	public Feedback addNewFeedback(Feedback feedback) {
		
		feedback.setTimeGiven(System.currentTimeMillis());
		Feedback persistedFeedback = feedbackRepo.save(feedback);
		
		if(persistedFeedback.getId() == 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "There was a problem during resource creation");
		}
		
		return persistedFeedback;
		
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) {

		Feedback updatedFeedback = feedbackRepo.save(feedback);
		
		if(updatedFeedback.getId() == 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "There was a problem during resource update");
		}
		
		return updatedFeedback;
		
	}
	
	
}
