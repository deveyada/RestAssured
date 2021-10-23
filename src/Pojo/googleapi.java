package Pojo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;

public class googleapi {
	
	@Test
	
	public void googlepostapi() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		GApidetails gd = given().queryParam("key", "qaclick123").expect().defaultParser(Parser.JSON)
		.when().post("/maps/api/place/add/json").as(GApidetails.class);
		
		gd.setAccuracy(50);
		gd.setAddress("29, side layout, cohen 09");
		gd.setLanguage("French-IN");
		gd.setName("Frontline house");
		gd.setPhone_number("(+91) 983 893 3937");
		gd.setWebsite("http://google.com");
		gd.setTypes(null);
	}

}
