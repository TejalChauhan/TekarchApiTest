package com.test.helpers;

import com.test.Constants.Endpoints;
import com.test.Utils.Utils;
import com.test.modules.LoginPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReusableMethods {

	public static Response loginToApp() {
		String username = Utils.getApp_Property("username");
		String password = Utils.getApp_Property("password");
		LoginPojo login = new LoginPojo();
		login.setUsername(username);
		login.setPassword(password);
		Response res = RestAssured.given().contentType(ContentType.JSON)
				.body(login)
				.when()
				.post(Endpoints.LOGIN);
		return res;
	}
}
