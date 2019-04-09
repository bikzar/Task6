package by.epam.javaweb.voitenkov.task6.util;

import by.epam.javaweb.voitenkov.task6.model.logic.parser.BankDOMParser;
import by.epam.javaweb.voitenkov.task6.model.logic.parser.BankSaxParser;
import by.epam.javaweb.voitenkov.task6.model.logic.parser.BankStAXParser;
import by.epam.javaweb.voitenkov.task6.model.logic.parser.parserinterface.Parser;
import by.epam.javaweb.voitenkov.task6.model.logic.validator.saxvalidator.SAXValidator;
import by.epam.javaweb.voitenkov.task6.model.logic.validator.validatorinterface.Validate;
import by.epam.javaweb.voitenkov.task6.model.logic.handler.saxhandler.SAXHandler;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 8, 2019
 */
public enum ParserFactory {

	SAXPARSER, STAXPARSER, DOMPARSER;

	private static final String PARSER = SysConfigurator.getProperty("parser");
	private static ParserFactory parserType;
	private static String xmlFile;
	private static String xsdFile;
	private static Validate validator;
	private static SAXHandler saxHandler;

	static {
		parserType = ParserFactory.valueOf(PARSER);
		xmlFile = SysConfigurator.getProperty("xmlBankFile");
		xsdFile = SysConfigurator.getProperty("xsdBankFile");
		validator = new SAXValidator(xmlFile, xsdFile);
		saxHandler = new SAXHandler();
	}

	public static Parser getParser() {

		Parser parser = BankDOMParser.getInstance(xmlFile, validator);

		switch (parserType) {
		case SAXPARSER:
			parser = BankSaxParser.getInstanse(validator, saxHandler, xmlFile);
			break;
		case STAXPARSER:
			parser = BankStAXParser.getInstance(validator, xmlFile);
		default:
		}

		return parser;
	}

}
