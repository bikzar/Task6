package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import by.epam.javaweb.voitenkov.task6.model.logic.validator.saxvalidator.SAXValidator;
import by.epam.javaweb.voitenkov.task6.model.logic.validator.validatorinterface.Validate;
import by.epam.javaweb.voitenkov.task6.util.SysConfigurator;

/**
 * @author Sergey Voitenkov
 *
 * Apr 9, 2019
 */
public class ValidatorFactory {

	private static String xmlFile = SysConfigurator.getProperty("xmlBankFile");
	private static String xsdFile = SysConfigurator.getProperty("xsdBankFile");
	private static Validate validator;
	
	static{
		validator = new SAXValidator(xmlFile, xsdFile);
	}
	
	
	private ValidatorFactory() {
	}

	
	public static Validate getVAlidator() {
		return validator;
	}
}
