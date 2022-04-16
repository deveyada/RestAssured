package libraryapi;

public class PayloadForLibraryAPI {

	public static String payloadla(String isbn,String aisle) {
		return "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foer\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
