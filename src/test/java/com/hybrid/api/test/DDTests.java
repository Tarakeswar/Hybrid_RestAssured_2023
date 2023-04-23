package com.hybrid.api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybrid.api.endPoints.UserEndPoints;
import com.hybrid.api.payload.User;
import com.hybrid.api.utilities.DataProviders;

import io.restassured.response.Response;

public class DDTests {
	
	
	@Test(priority = 1,dataProvider ="Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userId,String userName,String fName,String lName,String userEmail,String pwd,String phone) {
		
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userPayload);

		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2,dataProvider ="UserNames",dataProviderClass = DataProviders.class)
	public void testgetUserByName(String userName) {
		Response response = UserEndPoints.readUser(userName);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3,dataProvider ="UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response response = UserEndPoints.deleteUser(userName);

		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
