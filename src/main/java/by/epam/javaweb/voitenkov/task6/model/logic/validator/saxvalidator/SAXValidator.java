package by.epam.javaweb.voitenkov.task6.model.logic.validator.saxvalidator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import by.epam.javaweb.voitenkov.task6.model.logic.validator.validatorinterface.Validate;

/**
 * @author Sergey Voitenkov
 *
 *         Apr 6, 2019
 */
public class SAXValidator implements Validate {

	private static final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
	private static Logger logger;

	private String xmlFileName;
	private String xsdFile;

	static {
		logger = LogManager.getLogger();
	}

	public SAXValidator(String xmlFileName, String xsdFile) {

		if (xmlFileName != null) {
			this.xmlFileName = xmlFileName;
		}

		if (xsdFile != null) {
			this.xsdFile = xsdFile;
		}
	}

	public boolean isValid() {

		boolean resualt = true;
		try {
			if (xmlFileName != null && xsdFile != null) {

				SchemaFactory factory = SchemaFactory.newInstance(language);

				File schemaLocation = new File(xsdFile);

				Schema schema = factory.newSchema(schemaLocation);

				Validator validator = schema.newValidator();

				Source source = new StreamSource(xmlFileName);

				validator.validate(source);
			} else {
				resualt = false;
			}

		} catch (SAXException e) {
			resualt = false;
			logger.warn("Validation " + xmlFileName + " is not valid because "
					+ e.getMessage());
		} catch (IOException e) {
			resualt = false;
			logger.warn(
					xmlFileName + " is not valid because " + e.getMessage());
		}

		return resualt;
	}
}
