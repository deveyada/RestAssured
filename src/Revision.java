


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import Source.Payload;

public class Revision {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").headers("Content-Type","application/json")
		.when().body(Payload.body()).post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response().asPrettyString();
		
		JsonPath jsp = new JsonPath(response);
		String scope = jsp.get("scope");
		
		System.out.println(response);
		System.out.println(scope);

	}

}
