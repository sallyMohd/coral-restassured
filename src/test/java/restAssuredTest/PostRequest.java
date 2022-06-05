package restAssuredTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PostRequest {
	public static HashMap map= new HashMap();
	
	@BeforeClass
	public void data() {
		map.put("name", "test1");
		map.put("username", "test");
		
		ArrayList<String> profilevalues = new ArrayList<String>();
		profilevalues.add("firstName: m");
		profilevalues.add("lastName: s");
		profilevalues.add("orders");
		
		map.put("profile", profilevalues);
	
		
		RestAssured.baseURI= "https://620e3da1585fbc3359db4edf.mockapi.io/api/v1/users";
		
	}
	
	@Test
	public void postRequest() {
		given()
		.contentType(ContentType.JSON)
		.body(map)
		.when()
		.post("https://620e3da1585fbc3359db4edf.mockapi.io/api/v1/users")
		.then()
		.assertThat()
		.statusCode(201)
		.log().all()
		.extract()
	      .path("id");
		
		
		
	}
	@Test
	public void getrequest() {
		given()
		.when()
		.get("https://620e3da1585fbc3359db4edf.mockapi.io/api/v1/users/14")
		.then()
		.assertThat()
	    .body("[0].name", equalto("test1"));

	
		
	
	}

}
