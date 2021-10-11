package Source;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class jiraissuebasics {
	SessionFilter sf = new SessionFilter();
	@Test	
	public void createissue() {
		RestAssured.baseURI = "http://localhost:8080";
		
		//Create sessionid and save in sessionfilter
		
		given().header("Content-Type","application/json").
		body("{\n" + 
				"    \"username\": \"deveyada\",\n" + 
				"    \"password\": \"Cap@1234\"\n" + 
				"}").filter(sf).when().post("/rest/auth/1/session").then().extract().response().asString();
		/*System.out.println(resp);
		JsonPath jp = new JsonPath(resp);
		String token = jp.getJsonObject("session.value");*/		
		
		//Create issue
		
		String response = given().header("Content-Type","application/json")
		.body("{\n" + 
				"    \"fields\": {\n" + 
				"       \"project\":\n" + 
				"	   {\n" + 
				"          \"key\": \"RES\"\n" + 
				"       },\n" + 
				"       \"summary\": \"Issue number 7\",\n" + 
				"       \"description\": \"This is the seventh issue created through API\",\n" + 
				"       \"issuetype\": {\n" + 
				"          \"name\": \"Bug\"\n" + 
				"       }\n" + 
				"	   }\n" + 
				"	   }").filter(sf).when().post("/rest/api/2/issue").then().extract().response().asString();		
		System.out.println(response);
	
	
		//Add Comment
		
		String commentresponse = given().header("Content-Type","application/json").
		body("{\n" + 
				"    \"body\": \"Added a comment in the issue through automation.\",\n" + 
				"    \"visibility\": {\n" + 
				"        \"type\": \"role\",\n" + 
				"        \"value\": \"Administrators\"\n" + 
				"    }\n" + 
				"}").filter(sf).when().post("/rest/api/2/issue/10100/comment").then().extract()
		.response().asString();
		System.out.println(commentresponse);
		
		/*//Add attachment
		given().filter(sf).header("Content-Type","application/json")*/
	}

}

