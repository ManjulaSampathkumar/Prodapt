package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathFactory;

public class WebObjectLocators extends ObjectLocators {

	protected static SAXBuilder builder;
	protected static File xmlFile;

	public static final String TEST_ROOT_DIR = System.getProperty("user.dir");

	static {

		FileInputStream webStream = null;
		builder = new SAXBuilder();
		xmlFile = new File(new File(TEST_ROOT_DIR + File.separator + "ObjectRepo" + File.separator + "XML"
				+ File.separator + "Web_ObjectRepository.xml").getAbsolutePath());

		if (!xmlFile.exists()) {
			try {
				throw new Exception("WebObjectRepository.XML file not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retrieve the property value based on the property name
	 * 
	 * @param locatorName
	 * @return property value
	 * @throws CustomException
	 */
	public static String getLocator(String locatorName) throws Exception {
		if (props == null) {
			throw new Exception(
					"Failed to read: Web_ObjectRepository.properties -> It is either not present or not readable");

		}
		String locvalue = props.getProperty(locatorName);
		return locvalue;
	}

	/**
	 * Retrieve the property value based on the property name and page name from XML
	 * 
	 * @param locatorName
	 * @return property value
	 */
	public static String getLocatorfromXML(String PageName, String locatorName) {

		String locvalue = "";
		Document doc = null;
		try {
			doc = (Document) builder.build(xmlFile);
			Element root = doc.getRootElement();
			XPathFactory xpath = XPathFactory.instance();
			Element locator = (Element) xpath
					.compile("//page[@name='" + PageName + "']/object[@name='" + locatorName + "']")
					.evaluateFirst(root);
			locvalue = locator.getChildText("locatortype") + ";" + locator.getChildText("locatorvalue");
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			locvalue = "";
		}
		return locvalue;
	}
}
