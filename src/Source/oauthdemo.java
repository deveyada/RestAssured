package Source;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class oauthdemo {
	
	String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWguygae539wlIk5p0-zzQsYPs3FxTxq7wFsEd9TzRuIXoTZ_nOdM3cGkkH9A9SqAQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
	@Test
	
	public void oauthautomation() {
		
		//Fetching code out of url
		
		String codesplit = url.split("code=")[1];
		String actualcode = codesplit.split("&scope")[0];
		System.out.println(actualcode);
		
		//token generation
		
		String tokenresponse = given().urlEncodingEnabled(false).queryParam("code", actualcode)
		.queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath jp = new JsonPath(tokenresponse);
		String token = jp.getJsonObject("access_token");
		
		//hitting actual request
		
		String response = given().queryParam("access_token", token)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		System.out.println(response);
	}

}