package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;
import by.epam.javaweb.voitenkov.task6.model.logic.handler.saxhandler.SAXHandler;
import by.epam.javaweb.voitenkov.task6.model.logic.parser.parserinterface.Parser;
import by.epam.javaweb.voitenkov.task6.model.logic.validator.validatorinterface.Validate;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 6, 2019
 */
public class BankSaxParser implements Parser {

	private SAXHandler saxHandler;
	private XMLReader xmlReader;
	private Validate validator;
	private String xmlFilePath;
	private static BankSaxParser instance;

	private BankSaxParser(Validate validator, SAXHandler saxHandler, String xmlFilePath) {

		if(xmlFilePath != null) {
			this.xmlFilePath = xmlFilePath;
		}
		
		if (validator != null) {
			this.validator = validator;
		}

		if (saxHandler != null) {
			this.saxHandler = saxHandler;
		}

		try {

			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			spfactory.setNamespaceAware(true);
			SAXParser saxParser = spfactory.newSAXParser();
			xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(saxHandler);

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	public Bank parseBank() {

		Bank resualt = new Bank();
		
		if(validator != null && xmlFilePath != null && validator.isValid()) {
			
			try {
				
				xmlReader.parse(xmlFilePath);
				resualt = saxHandler.getBank();
				
			} catch (IOException e) {
				
			} catch (SAXException e) {
			
			}
			
		}
				
		return resualt;
	}

	public static BankSaxParser getInstanse(Validate validator, SAXHandler saxHandler, String xmlFilePath) {
		
		if(instance == null) {
			instance = new BankSaxParser(validator,saxHandler, xmlFilePath);
		}
		
		return instance;
	}
}
