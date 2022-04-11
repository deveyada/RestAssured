import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

import Source.Payload;

public class Revision {
	
	public static String placeid;

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").headers("Content-Type","application/json")
		.when().body(Payload.body()).post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response().asPrettyString();
		
		JsonPath jsp = new JsonPath(response);
		placeid = jsp.getString("place_id");
		
		System.out.println(response);
		System.out.println(placeid);
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.when().log().all().body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\"70 winter walk test, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		put("/maps/api/place/update/json").
		then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		String getresponse = given().queryParams("place_id",placeid,"key","qaclick123").when()
		.get("/maps/api/place/get/json").then().extract().response().asPrettyString();
		System.out.println(getresponse);
	}

}
