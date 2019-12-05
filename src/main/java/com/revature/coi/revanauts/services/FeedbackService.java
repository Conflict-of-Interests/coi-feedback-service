package com.revature.coi.revanauts.services;

import java.util.List;

import com.revature.coi.revanauts.models.Feedback;

public interface FeedbackService {
	
	List<Feedback> getAllFeedback();
	List<Feedback> getAllFeedbackForAssociate(Feedback feedback);
	List<Feedback> getAllFeedbackBySkill(Feedback feedback);
	Feedback getFeedbackById(Feedback feedback);
	boolean addNewFeedback(Feedback feedback);
	void updateFeedback(Feedback feedback);
	
}
