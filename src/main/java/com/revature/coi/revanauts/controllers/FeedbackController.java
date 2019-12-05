package com.revature.coi.revanauts.controllers;

import java.util.List;

import com.revature.coi.revanauts.models.Feedback;

public interface FeedbackController {
	
	List<Feedback> getAllFeedback();
	List<Feedback> getAllFeedbackForAssociate(Feedback feedback);
	List<Feedback> getAllFeedbackBySkill(Feedback feedback);
	Feedback getFeedbackById(Feedback feedback);
	Feedback addNewFeedback(Feedback feedback);
	Feedback updateFeedback(Feedback feedback);

}
