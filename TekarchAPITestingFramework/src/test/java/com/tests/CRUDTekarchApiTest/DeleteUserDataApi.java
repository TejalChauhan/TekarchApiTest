package com.tests.CRUDTekarchApiTest;

import org.testng.annotations.Test;

import com.test.Constants.Endpoints;
import com.test.Utils.Utils;
import com.test.helpers.UserServiceHelper;
import com.test.modules.DeleteUserPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class DeleteUserDataApi extends UserServiceHelper {
	
	@Test 
	
	public void deleteUserData_TekarchApi() {
		    String token = getToken(); 
			DeleteUserPojo deleteuser = new DeleteUserPojo();
			deleteuser.setUserid(Utils.getUpdateUserData_Property("userid"));
			deleteuser.setId(Utils.getUpdateUserData_Property("id"));
			Header header = new Header("token", token);
			Response response = RestAssured.given().contentType(ContentType.JSON).header(header)
					 .body(deleteuser)
					 .when().delete(Endpoints.DELETE_DATA);
			
		}
	

}
