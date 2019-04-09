package by.epam.javaweb.voitenkov.task6.controller;

import by.epam.javaweb.voitenkov.task6.model.logic.parser.parserinterface.Parser;
import by.epam.javaweb.voitenkov.task6.util.ParserFactory;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 4, 2019
 */
public class MainController {

	public static void main(String[] args) {
		
		Parser parser = ParserFactory.getParser();
		parser.parseBank();

	}

}
