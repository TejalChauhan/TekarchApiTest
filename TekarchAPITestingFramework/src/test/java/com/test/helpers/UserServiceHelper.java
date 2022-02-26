package com.test.helpers;

import org.testng.annotations.BeforeClass;

import com.test.Constants.Endpoints;
import com.test.Utils.Utils;
import com.test.modules.AddUserPojo;
import com.test.modules.DeleteUserPojo;
import com.test.modules.LoginPojo;
import com.test.modules.UpdateUserPojo;
import com.test.modules.UserPojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserServiceHelper {
	
 public static Response response;
 public static RequestSpecification requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
  
 @BeforeClass
	public 	static void initBaseUri() {
	RestAssured.baseURI = Utils.getApp_Property("BaseUrl");
//	RestAssured.registerParser("text/html", Parser.JSON);
}
  
	public static Response loginToApp() {
		String username = Utils.getApp_Property("username");
		String password = Utils.getApp_Property("password");
		LoginPojo login = new LoginPojo();
		login.setUsername(username);
		login.setPassword(password);
		response = RestAssured.given().contentType(ContentType.JSON)
				.body(login)
				.when()
				.post(Endpoints.LOGIN);
		return response;
	}
	public static String getBaseUri() {
		String baseUri = Utils.getApp_Property("BaseUrl");
		return baseUri;
	}
	public static String getToken() {
		response = loginToApp();
		String token = response.jsonPath().getString("[0].token");
		return token;
	}
	public static UserPojo[] getUserData(String token) {
		Header header = new Header("token", token);
		Response res = RestAssured.given().header(header)
				   .when().get(Endpoints.GET_DATA);
		
		UserPojo[] userList = res.as(UserPojo[].class);
	
		System.out.println("list of Users: "+userList.length);
		int status = res.getStatusCode();
		System.out.println("Status Code :"+status);
		res.then().statusCode(200);
		String accNo = res.jsonPath().getString("[0].accountno");
		System.out.println("First set of data for AccNo : "+accNo);
		return userList;
				
	}
	public static Response addUserData(String token) {
		AddUserPojo addUser = new AddUserPojo();
		addUser.setAccountno(Utils.getUserData_Property("accountno"));
		addUser.setDepartmentno(Utils.getUserData_Property("departmentno"));
		addUser.setSalary(Utils.getUserData_Property("salary"));
		addUser.setPincode(Utils.getUserData_Property("pincode"));
		Header header = new Header("token", token);
		response = RestAssured.given().contentType(ContentType.JSON).header(header)
				   .body(addUser)
				   .when().post(Endpoints.ADD_DATA);
		return response;
	}
	public static Response updateUserData(String token) {
		UserPojo[] userList = getUserData(token);
		String userId = userList[0].getUserid();
		String Id = userList[0].getId();
		UpdateUserPojo updateUser = new UpdateUserPojo();
		updateUser.setAccountno(Utils.getUpdateUserData_Property("accountno"));
		updateUser.setDepartmentno(Utils.getUpdateUserData_Property("departmentno"));
		updateUser.setSalary(Utils.getUpdateUserData_Property("salary"));
		updateUser.setPincode(Utils.getUpdateUserData_Property("pincode"));
		updateUser.setUserid(userId);
		updateUser.setId(Id);
		Header header = new Header("token", token);
		response = RestAssured.given().contentType(ContentType.JSON).header(header)
				.body(updateUser)
				.when().put(Endpoints.UPDATE_DATA);
		return response;
	}
	public static Response deleteUserData(String token) {
		UserPojo[] userList = getUserData(token);
		String userId = userList[0].getUserid();
		String Id = userList[0].getId();
		DeleteUserPojo deleteuser = new DeleteUserPojo();
		deleteuser.setUserid(userId);
		deleteuser.setId(Id);
		Header header = new Header("token", token);
		response = RestAssured.given().contentType(ContentType.JSON).header(header)
				 .body(deleteuser)
				 .when().delete(Endpoints.DELETE_DATA);
		return response;
				 
		
	}
	
}
