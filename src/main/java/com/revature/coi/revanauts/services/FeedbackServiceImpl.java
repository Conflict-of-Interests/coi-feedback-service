package com.revature.coi.revanauts.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revature.coi.revanauts.models.Feedback;
import com.revature.coi.revanauts.repos.FeedbackRepository;

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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid associate id provided");
		}
		
		return feedbackRepo.findFeedbackByAssociateId(associateId);
	}

	@Override
	public List<Feedback> getAllFeedbackBySkill(long skillId) {
		
		if (skillId > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value provided in request");
		}
		
		return feedbackRepo.findFeedbackBySkill(skillId);
		
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
		
		feedback.setTimeGiven(LocalDateTime.now());
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
