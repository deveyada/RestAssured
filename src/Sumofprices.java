import Source.Payload;
import io.restassured.path.json.JsonPath;

public class Sumofprices {

	public static void main(String[] args) {
		JsonPath abc = new JsonPath(Payload.samplejson());
		int purchaseamount = abc.getInt("dashboard.purchaseAmount");
		int sum = 0;
		for (int i=0;i<(abc.getInt("courses.size()"));i++)
		{
			int price = abc.getInt("courses["+i+"].price");
			int copies = abc.getInt("courses["+i+"].copies");
			int individualamount = price*copies;
			sum = sum + individualamount;
		}
		System.out.println(sum);
		if (purchaseamount==sum) {
			System.out.println("The Sum of all Course prices matches with Purchase Amount");
		}
		else {
			System.out.println("The Sum of all Course prices does not match with Purchase Amount");
		}
			
	}

}
