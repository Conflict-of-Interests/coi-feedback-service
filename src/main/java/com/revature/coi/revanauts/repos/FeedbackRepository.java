package com.revature.coi.revanauts.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.coi.revanauts.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	List<Feedback> findFeedbackByAssociateId(long associateId);
	List<Feedback> findFeedbackBySkill(long skillId);
	
}
