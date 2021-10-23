package Pojo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

public class coursedetails {
	
	String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjBDFp7Q37D8aTgoKuV0qu2xSSyUcDZ9WBuEKIHoUqzo3oBoKDShBDM7FIsmpIatg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
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
		
		courseparams cp = given().queryParam("access_token", token).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.as(courseparams.class);
		
		System.out.println(cp.getLinkedIn());
		System.out.println(cp.getCourses().getApi().get(0).getCourseTitle());
		System.out.println(cp.getCourses().getApi().get(0).getPrice());
		
		List<WebAutomation> wacourses = cp.getCourses().getWebAutomation();
		List<Api> apicourse = cp.getCourses().getApi();
		
		for (int i=0;i<wacourses.size();i++)
		{
			System.out.println(wacourses.get(i).getCourseTitle());
			System.out.println(wacourses.get(i).getPrice());
		}
		for (int j=0;j<apicourse.size();j++)
		{
			if (apicourse.get(j).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(cp.getCourses().getApi().get(j).getPrice());
			}
		}
	}

}