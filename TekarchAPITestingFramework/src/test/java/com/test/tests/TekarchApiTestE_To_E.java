package com.test.tests;

import java.io.File;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.test.Constants.SourcePath;
import com.test.helpers.UserServiceHelper;
import com.test.modules.UserPojo;

import io.restassured.module.jsv.JsonSchemaValidator;

public class TekarchApiTestE_To_E extends UserServiceHelper {
	public static String token;
	
	@Test  (priority = 0)
	
	public void TC_001_ValidLogin(){
		response = loginToApp();
		System.out.println("Login successfuly to TekarchApi");
		token = response.jsonPath().get("[0].token");
		System.out.println("Generated Token: "+token);
		int status = response.getStatusCode();
		System.out.println("Status Code :"+status);
		response.then().statusCode(201);			
	}
	
	@Test  (priority = 1)
	
    public void TC_002_addUserData(){
		response = addUserData(token);
		System.out.println("User data added successfully in TekarchApi");
		int status = response.getStatusCode();
		System.out.println("Status Code for add user data:"+status);
		String res_status = response.jsonPath().getString("status");
		System.out.println("The status for add user : "+res_status);
		response.then().assertThat().body("status", Matchers.equalTo("success"));
		
	}
	
	@Test (priority = 3)
	
    public void TC_003_getUserData() {
    	UserPojo[] userList = getUserData(token);
    	System.out.println("list of Users: "+userList.length);
    //	response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(SourcePath.USERDATA_JASONPATH)));
    	
    }
	
	@Test   (priority = 4)
	
    public void TC_004_updateUserData() {
		
		response = updateUserData(token);
		System.out.println("User data updated successfully in TekarchApi");
		int status = response.getStatusCode();
		System.out.println("Status Code Update :"+status);
		String res_status = response.jsonPath().getString("status");
		System.out.println("The status for update user data : "+res_status);
		response.then().assertThat().body("status", Matchers.equalTo("success"));
    	
    }
	
	@Test  (priority = 5)
	
    public void TC_005_deleteUserData() {
	//	token = getToken();
		response = deleteUserData(token);
		System.out.println("User data deleted successfully in TekarchApi");
		int status = response.getStatusCode();
		System.out.println("Status Code Delete:"+status);
		String res_status = response.jsonPath().getString("status");
		System.out.println("The status for Delete user data : "+res_status);
		response.then().assertThat().body("status", Matchers.equalTo("success"));
    }

}
