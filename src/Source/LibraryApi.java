package Source;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
public class LibraryApi {	
	
	
	@Test(dataProvider="data-provider")
	
	public void postrequest(String isbn, String aisle) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Response = given().header("Content-Type","application/json")
		.body("{\n" + 
				"\"name\":\"a new week day\",\n" + 
				"\"isbn\":\""+isbn+"\",\n" + 
				"\"aisle\":\""+aisle+"\",\n" + 
				"\"author\":\"english\"\n" + 
				"}").when().post("/Library/Addbook.php").then()
		.assertThat().statusCode(200)
		.extract().response().body().asPrettyString();
		System.out.println(Response);
	
		
	}

	/*@Test
	public void getbookdetails() {
		
		//JsonPath jp = new JsonPath()
		
	}*/
	
	@DataProvider(name = "data-provider")
	
	public String[][] bookdetails() {
		String[][] object = {{"devesh","289"},{"shweta","89094"},{"divisha","0098"}};
		return object;
	}

}
