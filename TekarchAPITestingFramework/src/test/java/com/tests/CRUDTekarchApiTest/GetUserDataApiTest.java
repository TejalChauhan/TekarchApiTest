package com.tests.CRUDTekarchApiTest;

import org.testng.annotations.Test;


import com.test.Constants.Endpoints;
import com.test.Constants.SourcePath;
import com.test.helpers.UserServiceHelper;
import com.test.modules.UserPojo;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

public class GetUserDataApiTest extends UserServiceHelper  {
	
	@Test(enabled = true)
	
	public void  getUserData_TekarchApi() {
	//	 File schema = new File(System.getProperty("user.dir")+"userdata.json");
		 String token = getToken();
		Header header = new Header("token", token);
		Response res = RestAssured.given().header(header)
				   .when().get(Endpoints.GET_DATA);
		System.out.println("status code="+res.getStatusCode());
	//	res.prettyPrint();
	//	res.then().body(matchesJsonSchemaInClasspath(System.getProperty("user.dir")+"userdata.json"));
		res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(SourcePath.USERDATA_JASONPATH)));
		UserPojo[] userList = res.body().as(UserPojo[].class);
		
		System.out.println("list of Users: "+userList.length);
		int status = res.getStatusCode();
		System.out.println("Status Code :"+status);
		
		String accNo = res.jsonPath().get("[0].accountno");
		System.out.println("First set of data for AccNo : "+accNo);
		
				
	}

}
