import Source.Payload;
import io.restassured.path.json.JsonPath;

public class Jsonparsing {

	public static void main(String[] args) {
		
		JsonPath jsp = new JsonPath(Payload.samplejson());			
		int numberofcourses = jsp.getInt("courses.size()");		
		System.out.println("The number of courses are " + numberofcourses);		
		String purchaseamount = jsp.getString("dashboard.purchaseAmount");		
		System.out.println("The purchaseamount is " + purchaseamount);
		String coursetitle = jsp.getString("courses[0].title");
		System.out.println("The first course title is "+coursetitle);
		for (int i=0;i<numberofcourses;i++)
		{
			String title = jsp.getString("courses["+i+"].title");
			System.out.println(title);
			int price = jsp.getInt("courses["+i+"].price");
			System.out.println("The price of "+title+" is "+price);
		}
		for(int j=0;j<numberofcourses;j++)
		{
			String titleresult = jsp.getString("courses["+j+"].title");
			if (titleresult.equals("Selenium Python")) {
				int copiessold = jsp.getInt("courses["+j+"].copies");
				System.out.println("The price of "+titleresult+" is "+copiessold);		
				break;
			}
			}
		}

	}

