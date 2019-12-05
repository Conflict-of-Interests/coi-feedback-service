package com.revature.coi.revanauts.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.coi.revanauts.exceptions.BadRequestException;
import com.revature.coi.revanauts.exceptions.ResourceCreationException;
import com.revature.coi.revanauts.exceptions.ResourceNotFoundException;
import com.revature.coi.revanauts.models.Feedback;
import com.revature.coi.revanauts.repos.FeedbackRepository;

// TODO replace persistent objects being returned by methods with dtos
@Transactional
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

	private FeedbackRepository feedbackRepo;
	
	@Autowired
	public FeedbackServiceImpl(FeedbackRepository repo) {
		this.feedbackRepo = repo;
	}

	@Override
	public List<Feedback> getAllFeedback() {
		return feedbackRepo.findAll();
	}

	@Override
	public List<Feedback> getAllFeedbackForAssociate(Feedback feedback) {
		
		long associateId = feedback.getAssociateId();
		
		if(associateId <= 0) {
			throw new BadRequestException("Invalid associate id provided");
		}
		
		return feedbackRepo.findFeedbackByAssociateId(associateId);
	}

	@Override
	public List<Feedback> getAllFeedbackBySkill(Feedback feedback) {
		
		if (feedback == null || feedback.getSkill() == null || feedback.getSkill().getId() > 0) {
			throw new BadRequestException("Invalid value provided in request");
		}
		
		return feedbackRepo.findFeedbackBySkill(feedback.getSkill());
		
	}

	@Override
	public Feedback getFeedbackById(Feedback feedback) {
		
		Optional<Feedback> _feedback = feedbackRepo.findById(feedback.getId());
		
		if(!_feedback.isPresent()) {
			throw new ResourceNotFoundException("No feedback found with provided id");
		}
		
		return _feedback.get();
		
	}

	@Override
	public Feedback addNewFeedback(@Valid Feedback feedback) {
		
		Feedback persistedFeedback = feedbackRepo.save(feedback);
		
		if(persistedFeedback.getId() == 0) {
			throw new ResourceCreationException("There was a problem during resource creation");
		}
		
		return persistedFeedback;
		
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) {

		Feedback updatedFeedback = feedbackRepo.save(feedback);
		
		if(updatedFeedback.getId() == 0) {
			throw new ResourceCreationException("There was a problem during resource update");
		}
		
		return updatedFeedback;
		
	}
	
	
}
