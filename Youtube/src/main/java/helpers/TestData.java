package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class loads the data from TestData property file
 * 
 */

public class TestData {

	private static Properties props = new Properties();

	static {
		FileInputStream in = null;
		try {
			in = new FileInputStream(System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "TestData.properties");
			props.load(in);
		} catch (IOException e) {
			System.err.println("Failed to read: " + "TestData.properties");
		}
	}

	/**
	 * Retrieve the property value based on the property name
	 * 
	 * @param locatorName
	 * @return property value
	 */
	public static String getData(String locatorName) {
		String locvalue = props.getProperty(locatorName);
		return locvalue;

	}
}