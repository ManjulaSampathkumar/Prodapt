package screen;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import helpers.CommonUtils;
import helpers.TestData;
import helpers.WebObjectLocators;
import junit.framework.Assert;

public class SearchandPlay extends CommonUtils {
	
	public static String searchBar = WebObjectLocators.getLocatorfromXML("Search", "SearchBar");
	public static String searchIcon = WebObjectLocators.getLocatorfromXML("Search", "SearchIcon");
	public static String searchMic = WebObjectLocators.getLocatorfromXML("Search", "SearchMic");
	public static String emptyState = WebObjectLocators.getLocatorfromXML("Search", "emptyState");
	public static String searchResults = WebObjectLocators.getLocatorfromXML("Search", "Searchresults");
	public static String pauseButton = WebObjectLocators.getLocatorfromXML("Playback", "Pause");
	public static String playButton = WebObjectLocators.getLocatorfromXML("Playback", "Play");
	public static String skipButton = WebObjectLocators.getLocatorfromXML("Playback", "SkipButton");
	public static String time = WebObjectLocators.getLocatorfromXML("Playback", "current time");

	/**
	 * This method is to assert elements in search bar
	 */
	public void assertSearch() {
		try {
			wait(elementToBeClickable(searchBar));
			Assert.assertTrue(isElementPresent(searchBar));
			Assert.assertTrue(isElementPresent(searchIcon));
			Assert.assertTrue(isElementPresent(searchMic));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Failed - Asset Search---------------------");
		}
	}
	
	/**
	 * This method is to validate emptystate of Search when invalid search term is entered
	 */
	public void emptystateValidation() {
		try {
			type(searchBar, TestData.getData("emptysearchtext"));
			clickOnElement(searchIcon);
			wait(elementToBeClickable(emptyState));
			Assert.assertTrue(isElementPresent(emptyState));
			System.out.println("Empty state for search is displayed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Failed - Empty state Search---------------------");
		}
	}
	
	/**
	 * This method is to select video from search based on user input
	 */
	public void selectVideoFromSearch() {
		try {
			clearText(searchBar);
			type(searchBar, TestData.getData("searchtext"));
			clickOnElement(searchIcon);
			wait(elementToBeClickable(searchResults));
			List<WebElement> result = getSimilarElements(searchResults);
			int i = Integer.parseInt(TestData.getData("videonumber"));
			result.get(i).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Failed - SelectVideo From search---------------------");
		}
	}
	
	/**
	 * This method is to validate Play and Pause functionality of the video
	 */
	public void playVideoValidation() {
		try {
			wait(elementToBeClickable(pauseButton));
			if(isElementPresent(skipButton)) {
				System.out.println("Ad is being played, Hence skipping it");
				clickOnElement(skipButton);
			}
			String currentTime = driver.findElement(By.xpath(time)).getText();
			if(!currentTime.equalsIgnoreCase("0:00") && isElementPresent(pauseButton)) {
				System.out.println("Video started Playing");
			}
			clickOnElement(pauseButton);
			Assert.assertTrue(isElementPresent(playButton));
			System.out.println("Video Paused");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------Failed - playVideoValidation---------------------");
		}
	}
}
