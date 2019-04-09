package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 8, 2019
 */
public class BankDOMParserTest extends ParserTest{
	
	@Test
	public void parseBankTest() {

		Bank parsBank = BankDOMParser.getInstance(xmlFile, validator).parseBank();
		
		assertEquals(bank, parsBank);
	}
}
