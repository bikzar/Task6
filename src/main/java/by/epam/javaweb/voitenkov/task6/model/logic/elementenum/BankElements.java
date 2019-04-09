package by.epam.javaweb.voitenkov.task6.model.logic.elementenum;

/**
 * @author Sergey Voitenkov
 *
 * Apr 7, 2019
 */
public enum BankElements {
	BANK("Bank"),
	CREDIT_LIST("credit_List"),
	ID_INTERMEDIARY_NUMBER("id_Intermediary_Number"),
	INTERMEDIARY_NAME("intermediary_Name"),
	CREDIT("Credit"),
	CREDIT_NAME("credit_Name"),
	CURRENCY_TYPE("currency_Type"),
	PERCENTAGE("percentage"),
	IS_ACTIVE("is_Active"),
	MAX_CREDIT_SIZE("max_Credit_Size"),
	IS_CLOSING("is_Closing"),
	IS_FIXED_PERCENTAGE("is_Fixed_Percentage"),
	COMPANY_SIZE("company_Size"),
	INDIV_CREDIT_TYPE("indiv_Credit_Type");
	
	private String value;
	
	private BankElements(String value) {
		this.value = value;
	}
	
	public String getValie() {
		return value;
	}
}
