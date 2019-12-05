package com.revature.coi.revanauts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.coi.revanauts.models.Feedback;
import com.revature.coi.revanauts.repos.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	private FeedbackRepository feedbackRepo;
	
	@Autowired
	public FeedbackServiceImpl(FeedbackRepository repo) {
		this.feedbackRepo = repo;
	}

	@Override
	public List<Feedback> getAllFeedback() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> getAllFeedbackForAssociate(Feedback feedback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> getAllFeedbackBySkill(Feedback feedback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback getFeedbackById(Feedback feedback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		
	}
	
	
}
