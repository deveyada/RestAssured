package Pojo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class googleapi {
	
	@Test
	
	public void googlepostapi() {
		
		GApidetails gd = new GApidetails();
		
		List<String> lst = new ArrayList<String>();
		lst.add(0, "shoe parkere");
		lst.add(1, "shop");
		Gapilocation a = new Gapilocation();
		a.setLat(-67.889);
		a.setLng(23.8787);
		
		gd.setAccuracy(50);
		gd.setAddress("29, side layout, cohen 09");
		gd.setLanguage("French-IN");
		gd.setName("Frontline house");
		gd.setPhone_number("(+91) 983 893 3937");
		gd.setWebsite("http://google.com");
		gd.setTypes(lst);
		gd.setLocation(a);		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Response = given().log().all().queryParam("key", "qaclick123").body(gd).expect().defaultParser(Parser.JSON)
		.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();	
		
		System.out.println(Response);
	}

}
