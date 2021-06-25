package test;

import org.testng.annotations.Test;
import screen.Login;
import screen.SearchandPlay;
import screen.UploadScreen;

public class ProdadptScenarios {
	
	/**
	 * This method will cover below Test cases
	 * TC1 - Invalid Login
	 * TC2 - Valid Login
	 * TC3 - Empty State of Search flow
	 * TC4 - Search valid term and select the video based on the user input
	 * TC5 - Play and Pause functionality
	 * TC6 - Video upload
	 */
	@Test()
	public void youTubeScenarios() {
		try {
			
			Login login = new Login();
			SearchandPlay sp = new SearchandPlay();
			UploadScreen upload = new UploadScreen();
			
			login.launchYoutube();
			login.inValidLogin();
			login.validLogin();
			
			sp.assertSearch();
			sp.emptystateValidation();
			sp.selectVideoFromSearch();
			sp.playVideoValidation();
			
			upload.channelNavigation();
			upload.videoUpload();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------YoutubeScenarios Failed ---------------------");
		}
	}

}
