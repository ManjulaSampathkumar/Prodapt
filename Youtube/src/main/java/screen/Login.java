package screen;

import helpers.CommonUtils;
import helpers.TestData;
import helpers.WebObjectLocators;
import junit.framework.Assert;

public class Login extends CommonUtils {
	
	public static String logo = WebObjectLocators.getLocatorfromXML("Login", "Logo");
	public static String signIn = WebObjectLocators.getLocatorfromXML("Login", "SignInButton");
	public static String enterEmail = WebObjectLocators.getLocatorfromXML("Login", "EnterEmail");
	public static String nextButton = WebObjectLocators.getLocatorfromXML("Login", "NextButton");
	public static String password = WebObjectLocators.getLocatorfromXML("Login", "Password");
	public static String incorrectText = WebObjectLocators.getLocatorfromXML("Login", "IncorrectPassword");
	public static String profileIcon = WebObjectLocators.getLocatorfromXML("Login", "ProfileIcon");
	
	
	/**
	 * This method is to launch the Youtube URL
	 */
	public void launchYoutube() {
		try {
			goToUrl(TestData.getData("YoutubeURL"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Failed to Launch Youtube---------------------");
		}
	}
	
	/**
	 * This method will test valid login scenario
	 */
	public void validLogin() {
		try {
			/*Assert.assertTrue(isElementPresent(logo));
			isElementClickable(signIn);
			clickOnElement(signIn);
			wait(elementToBeClickable(signIn));
			type(enterEmail, TestData.getData("email"));
			clickOnElement(nextButton);
			wait(elementToBeClickable(password));*/
			clearText(password);
			type(password, TestData.getData("validpassword"));
			clickOnElement(nextButton);
			wait(elementToBeClickable(profileIcon));
			Assert.assertTrue(isElementPresent(profileIcon));
			System.out.println("User Logged In");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Valid Login Failed ---------------------");
		}
	}
	
	/**
	 * This method will test Invalid login scenario
	 */
	public void inValidLogin() {
		try {
			Assert.assertTrue(isElementPresent(logo));
			isElementClickable(signIn);
			clickOnElement(signIn);
			wait(elementToBeClickable(enterEmail));
			type(enterEmail, TestData.getData("email"));
			clickOnElement(nextButton);
			wait(elementToBeClickable(password));
			type(password, TestData.getData("invalidPassword"));
			clickOnElement(nextButton);
			Assert.assertTrue(isElementPresent(incorrectText));
			System.out.println("Invalid Test Shown");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------InValid Login Failed ---------------------");
		}
	}
	
	

}
