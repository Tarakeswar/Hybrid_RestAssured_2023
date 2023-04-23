package com.hybrid.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.hybrid.api.endPoints.UserEndPoints;
import com.hybrid.api.endPoints.UserEndPoints2;
import com.hybrid.api.payload.User;

import io.restassured.response.Response;

public class UserTest2 {

	Faker faker;
	User userPayload;
	public Logger logger;

	@BeforeClass
	public void setUp() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//initiate the log
		logger=LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("**************** creating user **********************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User is created **********************");
	}

	@Test(priority = 2)
	public void getUserByName() {
		logger.info("**************** Reading user Info **********************");
		
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User Info is displayed**********************");
	}

	@Test(priority = 3)
	public void updateUserByName() {
		
		logger.info("**************** updating the user **********************");

		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());

		Response response = UserEndPoints2.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("**************** User is updated**********************");
	}

	@Test(priority = 4)
	public void deleteUserByName() {
		logger.info("**************** deleting the user **********************");
		
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** user is deleted  **********************");
	}

}
