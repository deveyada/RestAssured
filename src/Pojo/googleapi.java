package Pojo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class googleapi {
	
	RequestSpecBuilder req = new RequestSpecBuilder();
	ResponseSpecBuilder res = new ResponseSpecBuilder();
	
	@Test
	
	public void googlepostapi() {
		
		GApidetails gd = new GApidetails();
		
		req.setBaseUri("https://rahulshettyacademy.com");
		req.setBasePath("/maps/api/place/add/json");
		req.addQueryParam("key", "qaclick123");
		
		RequestSpecification rs = req.build();
		
		List<String> lst = new ArrayList<String>();
		lst.add(0, "newly created");
		lst.add(1, "shop");
		Gapilocation a = new Gapilocation();
		a.setLat(-67.889);
		a.setLng(23.667);
		
		gd.setAccuracy(50);
		gd.setAddress("29, side layout, cohen 09");
		gd.setLanguage("French-IN");
		gd.setName("Frontline house");
		gd.setPhone_number("(+91) 983 893 3937");
		gd.setWebsite("http://google.com");
		gd.setTypes(lst);
		gd.setLocation(a);		
		
		
		String Response = given().log().all().spec(rs).body(gd).expect().defaultParser(Parser.JSON)
		.when().post().then().extract().response().asString();
		
		res.expectStatusCode(200);
		
		System.out.println(Response);
	}

}
