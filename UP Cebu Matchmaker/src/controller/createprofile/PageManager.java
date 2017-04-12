package controller.createprofile;

public class PageManager {

	private static String firstPage = "/view/profile/FirstPage.fxml";
	private static String secondPage = "/view/profile/SecondPage.fxml";
	private static String thirdPage = "/view/profile/ThirdPage.fxml";
	private static String fourthPage = "/view/profile/FourthPage.fxml";
	
	public static String getPage(int pageNumber) {
		switch(pageNumber) {
		case 1:
			return firstPage;
		case 2:
			return secondPage;
		case 3:
			return thirdPage;
		case 4:
			return fourthPage;
		default:
			return "";
		}
	}
}
