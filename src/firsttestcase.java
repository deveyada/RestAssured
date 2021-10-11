import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class firsttestcase {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\n" + 
				"  \"location\": {\n" + 
				"    \"lat\": -38.383494,\n" + 
				"    \"lng\": 39.427362\n" + 
				"  },\n" + 
				"  \"accuracy\": 50,\n" + 
				"  \"name\": \"Frontlines house\",\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\n" + 
				"  \"address\": \"610, China Town Goa\",\n" + 
				"  \"types\": [\n" + 
				"    \"shoe park\",\n" + 
				"    \"shop\"\n" + 
				"  ],\n" + 
				"  \"website\": \"http://google.com\",\n" + 
				"  \"language\": \"French-IN\"\n" + 
				"}").when().log().all().post("/maps/api/place/add/json").then().log().all().extract().response();
		JsonPath path = new JsonPath(response.asString());
		String placeid = path.getJsonObject("place_id");
		
		given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123").
		queryParam("place_id", placeid)
		.body("{\n" + 
				"\"place_id\":\""+placeid+"\",\n" + 
				"\"address\":\"615 chhapri walk, USA\",\n" + 
				"\"key\":\"qaclick123\"\n" + 
				"}").when().log().all().put("/maps/api/place/update/json").then().log().all()
		.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));		
		
		Response updatedresponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().get("/maps/api/place/get/json").then().log().all().extract().response();
		
		System.out.println("The updated response is");
		System.out.println(updatedresponse.asPrettyString());
		
		
	}

}
