package libraryapi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class LibraryAPI {
	@Test
	
	public void postaction() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String resp = given().headers("Content-Type","application/json").body(PayloadForLibraryAPI.payloadla("tester","6676"))
		.when().post("/Library/Addbook.php").then().extract().response().asPrettyString();
		System.out.println(resp);
	}
	

}
