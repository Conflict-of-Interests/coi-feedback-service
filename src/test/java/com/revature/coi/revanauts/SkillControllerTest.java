package com.revature.coi.revanauts;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.coi.revanauts.models.Skill;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SkillControllerTest {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	public void getAllFeedbackSuccessTest() {
		get("/skills").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getAllFeedbackIfNotEmptyTest() {
		get("/skills").then().statusCode(200).body("results.size()", greaterThan(0));
	}
	
	@Test
	public void getSkillByIdSuccessTest() {
		get("/skills/id/3").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getUserByIdSuccessAndHasNameTest() {
		get("/skills/id/3").then().statusCode(200).body("name", equalTo("Critical Thinking"));
	}
	
	@Test
	public void getSkillByNameSuccessTest() {
		get("/skills/name/Critical Thinking").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getUserByNameSuccessAndHasNameTest() {
		get("/skills/name/Critical Thinking").then().statusCode(200).body("name", equalTo("Critical Thinking"));
	}
	
	@Test
	public void updateSkillTest() {
		with().body(new Skill(4L, "Test Skill", "This is a skill", "You will be fine"))
		.when()
		.contentType(ContentType.JSON)
		.request("PUT","/skills")
		.then()
		.statusCode(200);	
	}
	
	@Test
	public void updateSkillWithBadIdTest() {
		with().body(new Skill(0L, "Test Skill", "This is a skill", "You will be fine"))
		.when()
		.contentType(ContentType.JSON)
		.request("PUT","/skills")
		.then()
		.statusCode(400);
	}
}
