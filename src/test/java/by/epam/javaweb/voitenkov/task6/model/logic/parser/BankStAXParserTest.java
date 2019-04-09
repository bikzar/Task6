package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 9, 2019
 */
public class BankStAXParserTest extends ParserTest {

	@Test
	public void parseBankTest() {

		Bank parsBank = BankStAXParser.getInstance(validator, xmlFile)
				.parseBank();

		assertEquals(bank, parsBank);
	}

}
