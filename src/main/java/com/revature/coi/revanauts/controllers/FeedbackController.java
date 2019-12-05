package com.revature.coi.revanauts.controllers;

import java.util.List;

import com.revature.coi.revanauts.models.Feedback;

public interface FeedbackController {
	
	List<Feedback> getAllFeedback();
	List<Feedback> getAllFeedbackForAssociate(long associateId);
	List<Feedback> getAllFeedbackBySkill(long skillId);
	Feedback getFeedbackById(long id);
	Feedback addNewFeedback(Feedback feedback);
	Feedback updateFeedback(Feedback feedback);

}
