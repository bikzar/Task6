package by.epam.javaweb.voitenkov.task6.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 7, 2019
 */
public class SysConfigurator {

	private static final String CONFIG_FILE_PATH = "/home/sergey/eclipse-workspace/xmlparser/src/main/java/configurations.xml";

	private static Logger logger;
	private static Properties property;

	static {
		
		logger = LogManager.getLogger();
		
		property = new Properties();

		FileInputStream fileInputStream = null;

		try {

			fileInputStream = new FileInputStream(CONFIG_FILE_PATH);

			property.loadFromXML(fileInputStream);

		} catch (InvalidPropertiesFormatException e) {
			logger.warn(
					"Properties Format invalid in file: " + CONFIG_FILE_PATH,
					e);
		} catch (FileNotFoundException e) {
			logger.warn("Try to load configuration file. Cann't find file: "
					+ CONFIG_FILE_PATH, e);
		} catch (IOException e) {
			logger.warn(
					"Try to load property from XML file: " + CONFIG_FILE_PATH,
					e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.warn("Try to close FileInpurStream", e);
				}
			}
		}

	}

	private SysConfigurator() {
	}

	public static String getProperty(String key) {
		
		String resualt = "";
		
		if(key != null) {
			resualt = property.getProperty(key);
		}

		return resualt;
	}

	public static java.util.List<String> getItemList(String key) {

		java.util.List<String> list = new java.util.ArrayList<String>();
		
		if (key != null) {

			String filePath = property.getProperty(key);

			try (BufferedReader fileReader = new BufferedReader(
					new FileReader(filePath))) {

				String temp;

				try {
					while ((temp = fileReader.readLine()) != null) {
						list.add(temp);
					}
				} catch (IOException e) {
					logger.warn("Try to readLine in getItemList methood", e);
				}

			} catch (FileNotFoundException e) {
				logger.warn("Try to load " + key + "from file: " + filePath, e);

			} catch (IOException e) {
				logger.warn(
						"Try to close BufferedReader in getItemList methood",
						e);
			}
		}
		
		return list;
	}
}
