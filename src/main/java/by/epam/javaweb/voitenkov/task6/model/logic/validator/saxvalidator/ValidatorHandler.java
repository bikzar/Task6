package by.epam.javaweb.voitenkov.task6.model.logic.validator.saxvalidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Sergey Voitenkov
 *
 * Apr 6, 2019
 */
public class ValidatorHandler extends DefaultHandler  {
	
	private static Logger logger;
	
	private boolean isValid;
	
	static {
		logger =  LogManager.getLogger();
	}
	
	public ValidatorHandler() {
		isValid = true;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public void warning(SAXParseException exception){
		isValid = false;
		logger.warn(exception);
	}

	public void error(SAXParseException exception){
		isValid = false;
		logger.error(exception);
	}

	public void fatalError(SAXParseException exception){
		isValid = false;
		logger.fatal(exception);
	}
}
