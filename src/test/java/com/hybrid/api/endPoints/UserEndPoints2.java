package com.hybrid.api.endPoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import com.hybrid.api.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created t perform create,Read,update and delete request
public class UserEndPoints2 {
	
	//method created to get URL from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); // load properties file
		return routes;
	}

	public static Response createUser(User payload) {
		
		String post_url=getURL().getString("post_Url");
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);

		return response;
	}

	public static Response readUser(String userName) {
		String get_url=getURL().getString("get_Url");
		
		Response response = given().pathParam("username", userName)

				.when().get(get_url);

		return response;
	}

	public static Response updateUser(User payload, String userName) {
		String update_Url=getURL().getString("update_Url");
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(update_Url);

		return response;
	}

	public static Response deleteUser(String userName) {
		String delete_Url=getURL().getString("delete_Url");
		
		Response response = given().pathParam("username", userName).when().delete(delete_Url);

		return response;
	}

}
