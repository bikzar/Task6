package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.javaweb.voitenkov.task6.model.entity.credit.CompanyCredit;
import by.epam.javaweb.voitenkov.task6.model.entity.credit.Credit;
import by.epam.javaweb.voitenkov.task6.model.entity.credit.IndividualCredit;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CompanySizeType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CurrencyType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.IndividualCreditType;
import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;
import by.epam.javaweb.voitenkov.task6.model.logic.elementenum.BankElements;
import by.epam.javaweb.voitenkov.task6.model.logic.parser.parserinterface.Parser;
import by.epam.javaweb.voitenkov.task6.model.logic.validator.validatorinterface.Validate;
import by.epam.javaweb.voitenkov.task6.util.SysConfigurator;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 6, 2019
 */
public class BankDOMParser implements Parser {

	private static Logger logger;
	private static BankDOMParser instance;
	private static String creditType;
	private static String companyCredit;

	private Bank bank;
	private DocumentBuilder docBuilder;
	private String xmlFilePath;
	private Validate validator;

	static {
		logger = LogManager.getLogger();
		creditType = SysConfigurator.getProperty("creditType");
		companyCredit = SysConfigurator.getProperty("companyCredit");
	}

	private BankDOMParser(String xmlFilePath, Validate validator) {
		bank = new Bank();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.warn("Try to creat new DocBuilder \n" + e);
		}

		if (xmlFilePath != null) {
			this.xmlFilePath = xmlFilePath;
		}

		if (validator != null) {
			this.validator = validator;
		}
	}

	public static BankDOMParser getInstance(String xmlFilePath,
			Validate validator) {
		if (instance == null) {
			instance = new BankDOMParser(xmlFilePath, validator);
		}
		return instance;
	}

	@Override
	public Bank parseBank() {

		if (xmlFilePath != null && validator != null) {

			build();
		}

		return bank;
	}

	private void build() {
		Document doc = null;

		if (xmlFilePath != null) {
			try {

				doc = docBuilder.parse(xmlFilePath);

				Element root = doc.getDocumentElement();

				fillBankData(root);

				NodeList creditList = root.getElementsByTagName("Credit");

				for (int i = 0; i < creditList.getLength(); i++) {
					Element creditElem = (Element) creditList.item(i);
					Credit credit = buildCredit(creditElem);
					bank.addCreditToList(credit);
				}

			} catch (SAXException e) {
				logger.warn("Try to pars file: " + xmlFilePath + "\n" + e);
			} catch (IOException e) {
				logger.warn(
						"File error or I/O error: " + xmlFilePath + "\n" + e);
			}
		}
	}

	private void fillBankData(Element root) {

		bank.setIdNumberOfIntermediary(Integer.parseInt(getTextContent(root,
				BankElements.ID_INTERMEDIARY_NUMBER.getValie())));

		bank.setNameOfIntermediary(getTextContent(root,
				BankElements.INTERMEDIARY_NAME.getValie()));
	}

	private Credit buildCredit(Element creditElement) {

		Credit resualt = null;

		if (creditElement.getAttribute(creditType).equals(companyCredit)) {
			resualt = new CompanyCredit();
		} else {
			resualt = new IndividualCredit();
		}

		resualt.setActive("true".equals(getTextContent(creditElement,
				BankElements.IS_ACTIVE.getValie())));

		resualt.setClosing("true".equals(getTextContent(creditElement,
				BankElements.IS_CLOSING.getValie())));

		resualt.setCreditName(getTextContent(creditElement,
				BankElements.CREDIT_NAME.getValie()));

		resualt.setCurrencyType(CurrencyType.valueOf(getTextContent(
				creditElement, BankElements.CURRENCY_TYPE.getValie())));

		resualt.setFixedPercentage("true".equals(getTextContent(creditElement,
				BankElements.IS_FIXED_PERCENTAGE.getValie())));

		resualt.setMaxSizeOfCredit(Double.parseDouble(getTextContent(
				creditElement, BankElements.MAX_CREDIT_SIZE.getValie())));

		resualt.setPercentage(Double.parseDouble(getTextContent(creditElement,
				BankElements.PERCENTAGE.getValie())));

		if (resualt instanceof CompanyCredit) {
			((CompanyCredit) resualt).setSizeOfCompany(
					CompanySizeType.valueOf(getTextContent(creditElement,
							BankElements.COMPANY_SIZE.getValie())));
		} else {
			((IndividualCredit) resualt).setTypeOfIndivCredit(
					IndividualCreditType.valueOf(getTextContent(creditElement,
							BankElements.INDIV_CREDIT_TYPE.getValie())));
		}

		return resualt;
	}

	private String getTextContent(Element element, String elementName) {

		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
