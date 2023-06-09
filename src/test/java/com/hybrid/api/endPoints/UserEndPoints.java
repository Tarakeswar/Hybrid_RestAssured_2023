package com.hybrid.api.endPoints;

import static io.restassured.RestAssured.given;

import com.hybrid.api.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created t perform create,Read,update and delete request
public class UserEndPoints {

	public static Response createUser(User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_Url);

		return response;
	}

	public static Response readUser(String userName) {
		Response response = given().pathParam("username", userName)

				.when().get(Routes.get_Url);

		return response;
	}

	public static Response updateUser(User payload, String userName) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(Routes.update_Url);

		return response;
	}

	public static Response deleteUser(String userName) {
		Response response = given().pathParam("username", userName).when().delete(Routes.delete_Url);

		return response;
	}

}
