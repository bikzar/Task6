package by.epam.javaweb.voitenkov.task6.model.entity.credit;

import java.io.Serializable;
import java.text.DecimalFormat;

import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CurrencyType;

/**
 * @author Sergey Voitenkov March 16 2019
 */

public abstract class Credit implements Serializable {

	private static final long serialVersionUID = 3554703259301942616L;
	private static final String DEFAUL_NAME_OF_CREDIT = "Empty Name";
	private static final CurrencyType DEFAULT_TYPE_OF_CUR = CurrencyType.DOLLOR;

	private String creditName;
	private CurrencyType currencyType;
	private double percentage;
	private boolean isActive;
	private double maxSizeOfCredit;
	private boolean isClosing;
	private boolean isFixedPercentage;

	{
		creditName = DEFAUL_NAME_OF_CREDIT;
		currencyType = DEFAULT_TYPE_OF_CUR;
	}

	public Credit() {
	}

	public Credit(String creditName, CurrencyType currencyType,
			double percentage, boolean isActive, double maxSizeOfCredit,
			boolean isClosing, boolean isFixedPercentage) {

		if (creditName != null) {
			this.creditName = creditName;
		}

		if (currencyType != null) {
			this.currencyType = currencyType;
		}

		if (percentage > 0) {
			this.percentage = percentage;
		}

		if (maxSizeOfCredit > 0) {
			this.maxSizeOfCredit = maxSizeOfCredit;
		}

		this.isActive = isActive;
		this.isClosing = isClosing;
		this.isFixedPercentage = isFixedPercentage;
	}

	public Credit(Credit credit) {
		isActive = credit.isActive;
		isClosing = credit.isClosing;
		isFixedPercentage = credit.isFixedPercentage;
		creditName = credit.creditName;
		percentage = credit.percentage;
		currencyType = credit.currencyType;
		maxSizeOfCredit = credit.maxSizeOfCredit;
	}

	public abstract Credit getCloneOfCredit();

	public boolean extraEquals(Credit credit) {

		if (!(equalsSamePart(credit))) {
			return false;
		}

		if (!(this.isActive == true)
				|| !(Double.compare(percentage, credit.percentage) <= 0)
				|| !(Double.compare(maxSizeOfCredit,
						credit.maxSizeOfCredit) >= 0)) {
			return false;
		}

		return true;
	}

	public String getNameOfCredit() {
		return creditName;
	}

	public void setCreditName(String name) {
		if (name != null) {
			creditName = name;
		}
	}

	public CurrencyType getTypeOfCurrency() {
		return currencyType;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {

		if (percentage > 0) {
			this.percentage = percentage;
		} else {
			// throw new NegativeValueOfCreditPercentage();
		}

	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {

		if (currencyType != null) {
			this.currencyType = currencyType;
		}
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getMaxSizeOfCredit() {
		return maxSizeOfCredit;
	}

	public void setMaxSizeOfCredit(double maxSizeOfCredit) {

		if (maxSizeOfCredit > 0) {
			this.maxSizeOfCredit = maxSizeOfCredit;
		} else {
			// throw new NegativeValueOfMaxSizeOfCredit();
		}
	}

	public boolean isClosing() {
		return isClosing;
	}

	public void setClosing(boolean isClosing) {
		this.isClosing = isClosing;
	}

	public boolean isFixedPercentage() {
		return isFixedPercentage;
	}

	public void setFixedPercentage(boolean isFixedPercentage) {
		this.isFixedPercentage = isFixedPercentage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isClosing ? 1231 : 1237);
		result = prime * result + (isFixedPercentage ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(maxSizeOfCredit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((creditName == null) ? 0 : creditName.hashCode());
		temp = Double.doubleToLongBits(percentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((currencyType == null) ? 0 : currencyType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (!equalsSamePart(obj)) {
			return false;
		}

		Credit credit = (Credit) obj;

		if (creditName == null) {
			if (credit.creditName != null) {
				return false;
			}
		} else if (!creditName.equals(credit.creditName)) {
			return false;
		}

		if (isActive != credit.isActive
				|| Double.doubleToLongBits(percentage) != Double
						.doubleToLongBits(credit.percentage)
				|| Double.doubleToLongBits(maxSizeOfCredit) != Double
						.doubleToLongBits(credit.maxSizeOfCredit)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {

		DecimalFormat decimalFormat = new DecimalFormat("#.00");

		return "nameOfDeposit = " + creditName + ", typeOfCurrency = "
				+ currencyType + ", percentage = "
				+ String.format("%.2f", percentage) + ", isActive = " + isActive
				+ ", maxSizeOfCredit = " + decimalFormat.format(maxSizeOfCredit)
				+ ", isClosing = " + isClosing + ", isFixedPercentage = "
				+ isFixedPercentage;
	}

	private boolean equalsSamePart(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		Credit credit = (Credit) obj;

		if (isClosing != credit.isClosing
				|| isFixedPercentage != credit.isFixedPercentage
				|| !(currencyType == credit.currencyType)) {
			return false;
		}

		return true;
	}

}
