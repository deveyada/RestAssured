import Source.testbody;
import io.restassured.path.json.JsonPath;

public class sandbox {

	public static void main(String[] args) {
		JsonPath jp = new JsonPath(testbody.coursedetails());
		int purchaseamount = jp.getJsonObject("dashboard.purchaseAmount");
		System.out.println(purchaseamount);
		int coursesize = jp.getJsonObject("courses.size()");
		System.out.println(coursesize);
		String firstcoursetitle = jp.getJsonObject("courses[2].title");
		System.out.println(firstcoursetitle);
		int sum=0;
		for (int i=0;i<coursesize;i++) {
			String coursetitle = jp.getJsonObject("courses["+i+"].title");
			int courseprice = jp.getJsonObject("courses["+i+"].price");
			int coursecopies = jp.getJsonObject("courses["+i+"].copies");
			System.out.println("The title of course "+i+" is "+coursetitle);
			System.out.println("The price of course "+i+" is "+courseprice);
		
		int amount = courseprice * coursecopies;
		sum = sum + amount;
		
		}
		System.out.println("The sum of the prices is "+sum);
		
		
		int copiessold = jp.getJsonObject("courses[2].copies");
		System.out.println("The copies sold for RPA are "+copiessold);
		
	}

}
