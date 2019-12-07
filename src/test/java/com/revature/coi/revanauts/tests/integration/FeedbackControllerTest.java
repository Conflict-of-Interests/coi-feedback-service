package com.revature.coi.revanauts.tests.integration;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.coi.revanauts.models.Feedback;
import com.revature.coi.revanauts.models.Skill;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class FeedbackControllerTest {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void getAllFeedbackSuccessTest() {
		get("/feedback").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getFeedbackByIdWithValidIdTest() {
		get("/feedback/id/1").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getFeedbackByIdWithInvalidIdTest() {
		get("/feedback/id/0").then().assertThat().statusCode(400);
	}
	
	@Test
	public void getFeedbackByIdWithValidIdNotFoundTest() {
		get("/feedback/id/100000").then().assertThat().statusCode(404);
	}
	
	@Test
	public void getAssociateFeedbackWithValidAssociateIdTest() {
		get("/feedback/associates/1").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getAssociateFeedbackWithInvalidAssociateIdTest() {
		get("/feedback/associates/0").then().assertThat().statusCode(400);
	}
	
	@Test
	public void getAssociateFeedbackWithValidAssociateIdNotFoundTest() {
		get("/feedback/associates/100000").then().assertThat().statusCode(404);
	}
	
	@Test
	public void getSkillFeedbackWithValidSkillIdTest() {
		get("/feedback/skills/1").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getSkillFeedbackWithInvalidSkillIdTest() {
		get("/feedback/skills/0").then().assertThat().statusCode(400);
	}
	
	@Test
	public void getSkillFeedbackWithValidSkillIdNotFoundTest() {
		get("/feedback/skills/100000").then().assertThat().statusCode(404);
	}
	
	@Test
	public void postNewValidFeedbackTest() {
		with().body(new Feedback(0L, 1L, new Skill(1L, "Test skill", "This is a test skill", "Do better."), System.currentTimeMillis(), "Constructive feedback notes", true))
			.when()
			.contentType(ContentType.JSON)
			.request("POST", "/feedback")
			.then()
			.statusCode(201);	
	}
	
	@Test
	public void postInvalidFeedbackTest() {
		with().body(new Feedback())
			.when()
			.contentType(ContentType.JSON)
			.request("POST", "/feedback")
			.then()
			.statusCode(201);	
	}
	
}
