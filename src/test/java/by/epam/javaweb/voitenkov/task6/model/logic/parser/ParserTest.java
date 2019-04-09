package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;
import by.epam.javaweb.voitenkov.task6.model.logic.validator.validatorinterface.Validate;
import by.epam.javaweb.voitenkov.task6.util.SysConfigurator;

/**
 * @author Sergey Voitenkov
 *
 * Apr 9, 2019
 */
public class ParserTest {
	
	protected Bank bank;
	protected String xmlFile;
	protected Validate validator;

	public ParserTest() {
		bank = ExpectedBank.getBank();
		validator = ValidatorFactory.getVAlidator();
		xmlFile = SysConfigurator.getProperty("xmlBankFile");
	}

}
