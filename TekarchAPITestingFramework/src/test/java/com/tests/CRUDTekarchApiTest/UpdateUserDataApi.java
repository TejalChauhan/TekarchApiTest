package com.tests.CRUDTekarchApiTest;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.test.Constants.Endpoints;
import com.test.Utils.Utils;
import com.test.helpers.UserServiceHelper;
import com.test.modules.UpdateUserPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UpdateUserDataApi extends UserServiceHelper{
	
	@Test 
	
	public void updateUserData_TekarchApi() {
		String token = getToken(); 
		UpdateUserPojo updateUser = new UpdateUserPojo();
		updateUser.setAccountno(Utils.getUpdateUserData_Property("accountno"));
		updateUser.setDepartmentno(Utils.getUpdateUserData_Property("departmentno"));
		updateUser.setSalary(Utils.getUpdateUserData_Property("salary"));
		updateUser.setPincode(Utils.getUpdateUserData_Property("pincode"));
		updateUser.setUserid(Utils.getUpdateUserData_Property("userid"));
		updateUser.setId(Utils.getUpdateUserData_Property("id"));
		Header header = new Header("token", token);
		Response response = RestAssured.given().contentType(ContentType.JSON).header(header)
					.body(updateUser)
					.when().put(Endpoints.UPDATE_DATA);
		System.out.println("User data updated successfully in TekarchApi");
		int status = response.getStatusCode();
		System.out.println("Status Code Update :"+status);
		String res_status = response.jsonPath().getString("status");
		System.out.println("The status for update user data : "+res_status);
		response.then().assertThat().body("status", Matchers.equalTo("success"));
			
		}

}
