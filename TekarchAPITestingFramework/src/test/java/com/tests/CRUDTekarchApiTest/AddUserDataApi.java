package com.tests.CRUDTekarchApiTest;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.Constants.Endpoints;
import com.test.Utils.Utils;
import com.test.helpers.UserServiceHelper;
import com.test.modules.AddUserPojo;
import com.test.modules.LoginPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddUserDataApi extends UserServiceHelper {
	
	@Test 
	
	public void addUserData_TekarchApi() {
		String token = getToken(); 
		AddUserPojo addUser = new AddUserPojo();
		addUser.setAccountno(Utils.getUserData_Property("accountno"));
		addUser.setDepartmentno(Utils.getUserData_Property("departmentno"));
		addUser.setSalary(Utils.getUserData_Property("salary"));
		addUser.setPincode(Utils.getUserData_Property("pincode"));
		Header header = new Header("token",token);
		response = RestAssured.given().contentType(ContentType.JSON).header(header)
				   .body(addUser)
				   .when().post(Endpoints.ADD_DATA);
		System.out.println("User data added successfully in TekarchApi");
		int status = response.getStatusCode();
		System.out.println("Status Code for add user data:"+status);
		String res_status = response.jsonPath().getString("status");
		System.out.println("The status for add user : "+res_status);
		response.then().assertThat().body("status", Matchers.equalTo("success"));
	}

}
