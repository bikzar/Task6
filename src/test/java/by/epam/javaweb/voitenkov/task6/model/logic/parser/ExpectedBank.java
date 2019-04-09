package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import by.epam.javaweb.voitenkov.task6.model.entity.credit.CompanyCredit;
import by.epam.javaweb.voitenkov.task6.model.entity.credit.Credit;
import by.epam.javaweb.voitenkov.task6.model.entity.credit.IndividualCredit;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CompanySizeType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CurrencyType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.IndividualCreditType;
import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 8, 2019
 */
public class ExpectedBank {

	private static Bank bank;

	static {
		
		bank = new Bank();
		
		bank.setIdNumberOfIntermediary(15);
		bank.setNameOfIntermediary("BNB Bank");

		Credit credit = new CompanyCredit();

		credit.setActive(true);
		credit.setClosing(true);
		credit.setCreditName("First Step");
		credit.setCurrencyType(CurrencyType.DOLLOR);
		credit.setFixedPercentage(true);
		credit.setMaxSizeOfCredit(500);
		credit.setPercentage(16.2);
		((CompanyCredit) credit).setSizeOfCompany(CompanySizeType.MEDIUM);

		for (int i = 0; i < 3; i++) {
			bank.addCreditToList(credit);
		}

		credit = new IndividualCredit();

		credit.setActive(true);
		credit.setClosing(true);
		credit.setCreditName("First Step");
		credit.setCurrencyType(CurrencyType.DOLLOR);
		credit.setFixedPercentage(true);
		credit.setMaxSizeOfCredit(500000);
		credit.setPercentage(2.2);
		((IndividualCredit) credit)
				.setTypeOfIndivCredit(IndividualCreditType.FOR_HOUSE);

		bank.addCreditToList(credit);
	}

	private ExpectedBank() {
	}

	public static Bank getBank() {
		return bank.getCloneOfInstance();
	}
}
