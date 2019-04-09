package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class BankStAXParser implements Parser {

	private static BankStAXParser instance;
	private static Logger logger;
	private static String companyCredit;

	private Validate validator;
	private String xmlFilePath;
	private Bank bank;
	private Credit credit;
	private XMLInputFactory inputFact;
	private BankElements currentElemetn;

	static {
		logger = LogManager.getLogger();
		companyCredit = SysConfigurator.getProperty("companyCredit");
	}

	private BankStAXParser(Validate validator, String xmlFrilePath) {
		if (validator != null) {
			this.validator = validator;
		}

		if (xmlFrilePath != null) {
			this.xmlFilePath = xmlFrilePath;
		}

		inputFact = XMLInputFactory.newInstance();

		bank = new Bank();
	}

	public static BankStAXParser getInstance(Validate validator,
			String xmlFilePath) {
		if (instance == null) {
			instance = new BankStAXParser(validator, xmlFilePath);
		}

		return instance;
	}

	@Override
	public Bank parseBank() {

		if (xmlFilePath != null && validator != null && validator.isValid()) {
			buildCredits();
		}
		return bank;
	}

	private void buildCredits() {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;

		try {
			inputStream = new FileInputStream(new File(xmlFilePath));
			reader = inputFact.createXMLStreamReader(inputStream);

			while (reader.hasNext()) {

				int type = reader.next();

				if (type == 1) {

					switch (BankElements
							.valueOf(reader.getLocalName().toUpperCase())) {
					case ID_INTERMEDIARY_NUMBER:
						currentElemetn = BankElements.ID_INTERMEDIARY_NUMBER;
						break;
					case INTERMEDIARY_NAME:
						currentElemetn = BankElements.INTERMEDIARY_NAME;
						break;
					case CREDIT:
						currentElemetn = BankElements.CREDIT;

						if (companyCredit
								.contentEquals(reader.getAttributeValue(0))) {
							credit = new CompanyCredit();
						} else {
							credit = new IndividualCredit();
						}
						break;

					case CREDIT_NAME:
						currentElemetn = BankElements.CREDIT_NAME;
						break;
					case CURRENCY_TYPE:
						currentElemetn = BankElements.CURRENCY_TYPE;
						break;
					case PERCENTAGE:
						currentElemetn = BankElements.PERCENTAGE;
						break;
					case IS_ACTIVE:
						currentElemetn = BankElements.IS_ACTIVE;
						break;
					case MAX_CREDIT_SIZE:
						currentElemetn = BankElements.MAX_CREDIT_SIZE;
						break;
					case IS_CLOSING:
						currentElemetn = BankElements.IS_CLOSING;
						break;
					case IS_FIXED_PERCENTAGE:
						currentElemetn = BankElements.IS_FIXED_PERCENTAGE;
						break;
					case COMPANY_SIZE:
						currentElemetn = BankElements.COMPANY_SIZE;
						break;
					case INDIV_CREDIT_TYPE:
						currentElemetn = BankElements.INDIV_CREDIT_TYPE;
						break;
					default:
					}
						

				}

				if (type == 4) {

					String value = reader.getText();

					if (currentElemetn != null && value.matches("[^\\n\\t]+")) {

						switch (currentElemetn) {

						case ID_INTERMEDIARY_NUMBER:
							bank.setIdNumberOfIntermediary(
									Integer.parseInt(value));
							break;
						case INTERMEDIARY_NAME:
							bank.setNameOfIntermediary(value);
							break;
						case CREDIT_NAME:
							credit.setCreditName(value);
							break;
						case CURRENCY_TYPE:
							credit.setCurrencyType(CurrencyType.valueOf(value));
							break;
						case PERCENTAGE:
							credit.setPercentage(Double.parseDouble(value));
							break;
						case IS_ACTIVE:
							credit.setActive("true".equals(value));
							break;
						case MAX_CREDIT_SIZE:
							credit.setMaxSizeOfCredit(
									Double.parseDouble(value));
							break;
						case IS_CLOSING:
							credit.setClosing("true".equals(value));
							break;
						case IS_FIXED_PERCENTAGE:
							credit.setFixedPercentage("true".equals(value));
							break;
						case COMPANY_SIZE:
							((CompanyCredit) credit).setSizeOfCompany(
									CompanySizeType.valueOf(value));
							break;
						case INDIV_CREDIT_TYPE:
							((IndividualCredit) credit).setTypeOfIndivCredit(
									IndividualCreditType.valueOf(value));
							break;
						default:
						}
					}
				}

				if (type == 2) {

					String localNameInUpper = reader.getLocalName()
							.toUpperCase();

					currentElemetn = BankElements.valueOf(localNameInUpper);

					if (BankElements.CREDIT.equals(currentElemetn)) {
						bank.addCreditToList(credit);
					}
				}

			}

		} catch (FileNotFoundException e) {
			logger.warn("File with path: " + xmlFilePath + "not found.\n", e);
		} catch (XMLStreamException e) {
			logger.warn("StAX parsing error! " + e.getMessage() + "\n", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.warn("Impossible close file " + xmlFilePath + ":\n" + e);
			}
		}
	}
}
