package screen;

import org.openqa.selenium.By;
import org.testng.Assert;
import helpers.CommonUtils;
import helpers.TestData;
import helpers.WebObjectLocators;

public class UploadScreen extends CommonUtils {
	
	public static String profileIcon = WebObjectLocators.getLocatorfromXML("Login", "ProfileIcon");
	public static String createChannel = WebObjectLocators.getLocatorfromXML("Upload", "createChannel");
	public static String channelPopup = WebObjectLocators.getLocatorfromXML("Upload", "CreateChannelPopup");
	public static String yourChannel = WebObjectLocators.getLocatorfromXML("Upload", "YourChannel");
	public static String upload = WebObjectLocators.getLocatorfromXML("Upload", "upload");
	public static String selectFile = WebObjectLocators.getLocatorfromXML("Upload", "select");
	public static String complete = WebObjectLocators.getLocatorfromXML("Upload", "uploadcomplete");
	public static String next = WebObjectLocators.getLocatorfromXML("Upload", "nextButton");
	public static String kidsOption = WebObjectLocators.getLocatorfromXML("Upload", "kidsButton");
	public static String privateOption = WebObjectLocators.getLocatorfromXML("Upload", "Private");
	public static String save = WebObjectLocators.getLocatorfromXML("Upload", "Save");
	
	/**
	 * This method is to navigate to Channel page
	 */
	public void channelNavigation() {
		try {
			clickOnElement(profileIcon);
			if(isElementPresent(createChannel)) {
				clickOnElement(channelPopup);
			}else {
				clickOnElement(yourChannel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Failed to channelNavigation---------------------");
		}
	}
	
	/**
	 * This method is to validate video upload functionality
	 */
	public void videoUpload() {
		try {
			wait(elementToBeClickable(upload));
			String relPath = TestData.getData("videoPath");
			String path = System.getProperty("user.dir")+relPath;
			driver.findElement(By.xpath("//*[text()='Select files']")).sendKeys(path);
			wait(elementToBeClickable(complete));
			clickOnElement(next);
			clickOnElement(kidsOption);
			clickOnElement(next);
			clickOnElement(next);
			clickOnElement(next);
			clickOnElement(privateOption);
			clickOnElement(save);
			String title = TestData.getData("videoTitle"); 
			String uploadedVideo = "xpath;//a[@aria-label='"+title+"']";
			Assert.assertTrue(isElementPresent(uploadedVideo));
			System.out.println("Video Uploaded Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Failed videoUpload---------------------");
		}
	}
}
