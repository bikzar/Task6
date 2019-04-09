package by.epam.javaweb.voitenkov.task6.model.logic.handler.saxhandler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.javaweb.voitenkov.task6.model.entity.credit.CompanyCredit;
import by.epam.javaweb.voitenkov.task6.model.entity.credit.Credit;
import by.epam.javaweb.voitenkov.task6.model.entity.credit.IndividualCredit;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CompanySizeType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CurrencyType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.IndividualCreditType;
import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;
import by.epam.javaweb.voitenkov.task6.model.logic.elementenum.BankElements;
import by.epam.javaweb.voitenkov.task6.util.SysConfigurator;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 6, 2019
 */
public class SAXHandler extends DefaultHandler {

	private static String companyCredit;
	private static String individualCredit;

	private Bank bank;
	private BankElements tempElement;
	private Credit tempCredit;

	static {
		companyCredit = SysConfigurator.getProperty("companyCredit");
		individualCredit = SysConfigurator.getProperty("individualCredit");
	}

	public SAXHandler() {
		bank = new Bank();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		if (BankElements.CREDIT.name().equals(localName.toUpperCase())) {

			if (attributes != null && attributes.getLength() > 0) {

				tempElement = BankElements.valueOf(localName.toUpperCase());

				if (companyCredit.equals(attributes.getValue(0))) {
					tempCredit = new CompanyCredit();
				}

				if (individualCredit.equals(attributes.getValue(0))) {
					tempCredit = new IndividualCredit();
				}
			}

		} else {

			tempElement = BankElements.valueOf(localName.toUpperCase());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {

		String value = new String(ch, start, length).trim();

		if (!value.equals("")) {

			switch (tempElement) {

			case ID_INTERMEDIARY_NUMBER:
				bank.setIdNumberOfIntermediary(Integer.parseInt(value));
				break;
			case INTERMEDIARY_NAME:
				bank.setNameOfIntermediary(value);
				break;
			case CREDIT_NAME:
				tempCredit.setCreditName(value);
				break;
			case CURRENCY_TYPE:
				tempCredit.setCurrencyType(
						CurrencyType.valueOf(value.toUpperCase()));
				break;
			case PERCENTAGE:
				tempCredit.setPercentage(Double.parseDouble(value));
				break;
			case IS_ACTIVE:
				tempCredit.setActive("true".equals(value));
				break;
			case MAX_CREDIT_SIZE:
				tempCredit.setMaxSizeOfCredit(Double.parseDouble(value));
				break;
			case IS_CLOSING:
				tempCredit.setClosing("true".equals(value));
				break;
			case IS_FIXED_PERCENTAGE:
				tempCredit.setFixedPercentage("true".equals(value));
				break;
			case COMPANY_SIZE:
				((CompanyCredit) tempCredit)
						.setSizeOfCompany(CompanySizeType.valueOf(value));
				break;
			case INDIV_CREDIT_TYPE:
				((IndividualCredit) tempCredit).setTypeOfIndivCredit(
						IndividualCreditType.valueOf(value));
				break;
			default:
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {

		if (BankElements.CREDIT.name().equals(localName.toUpperCase())) {
			bank.addCreditToList(tempCredit);
		}
	}

	public Bank getBank() {
		return bank;
	}
}
