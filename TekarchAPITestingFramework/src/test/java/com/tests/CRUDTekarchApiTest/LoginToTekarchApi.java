package com.tests.CRUDTekarchApiTest;

import org.testng.annotations.Test;

import com.test.Constants.Endpoints;
import com.test.Utils.Utils;

import com.test.modules.LoginPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginToTekarchApi {
	
	@Test (enabled = false)
	
	public void loginToApp() {
		String username = Utils.getApp_Property("username");
		String password = Utils.getApp_Property("password");
		LoginPojo login = new LoginPojo();
		login.setUsername(username);
		login.setPassword(password);
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.body(login)
				.when()
				.post(Endpoints.LOGIN);
		System.out.println("Login successfuly to TekarchApi");
		String token = response.jsonPath().get("[0].token");
		System.out.println("Generated Token: "+token);
		int status = response.getStatusCode();
		System.out.println("Status Code :"+status);
		response.then().statusCode(201);	
		
	}
	

}
