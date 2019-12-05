package com.revature.coi.revanauts.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.coi.revanauts.models.Feedback;
import com.revature.coi.revanauts.models.Skill;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	List<Feedback> findFeedbackByAssociateId(Long associateId);
	List<Feedback> findFeedbackBySkill(Skill skill);
	
}
