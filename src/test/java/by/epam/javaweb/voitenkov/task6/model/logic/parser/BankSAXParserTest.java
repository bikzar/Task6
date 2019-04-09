package by.epam.javaweb.voitenkov.task6.model.logic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import by.epam.javaweb.voitenkov.task6.model.entity.intermed.Bank;
import by.epam.javaweb.voitenkov.task6.model.logic.handler.saxhandler.SAXHandler;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 9, 2019
 */
public class BankSAXParserTest extends ParserTest {

	private SAXHandler saxHandler = new SAXHandler();

	@Test
	public void parseBankTest() {

		Bank parsBank = BankSaxParser
				.getInstanse(validator, saxHandler, xmlFile).parseBank();

		assertEquals(bank, parsBank);
	}

}
