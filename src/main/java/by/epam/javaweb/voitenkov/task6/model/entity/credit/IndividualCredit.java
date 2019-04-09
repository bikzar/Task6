package by.epam.javaweb.voitenkov.task6.model.entity.credit;

import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CurrencyType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.IndividualCreditType;

/**
 * @author Sergey Voitenkov March 16 2019
 */

public class IndividualCredit extends Credit {

	private static final long serialVersionUID = -4957333909814503343L;
	private IndividualCreditType indivCreditType = IndividualCreditType.CONSUMER_LOAN;

	public IndividualCredit() {
	}

	public IndividualCredit(
			IndividualCreditType indivCreditType,
			String creditName, CurrencyType currencyType,
			double percentage, boolean isActive,
			double maxSizeOfCredit, boolean isClosing,
			boolean isFixedPercentage) {

		super(creditName, currencyType, percentage, isActive,
				maxSizeOfCredit, isClosing, isFixedPercentage);

		if (indivCreditType != null) {
			this.indivCreditType = indivCreditType;
		}
	}

	@Override
	public boolean extraEquals(Credit credit) {

		if (!super.extraEquals(credit)
				|| credit.getClass() != this.getClass()) {
			return false;
		}

		IndividualCredit individualsCredit = (IndividualCredit) credit;

		if (indivCreditType != individualsCredit.indivCreditType) {
			return false;
		}

		return true;

	}

	@Override
	public IndividualCredit getCloneOfCredit() {

		return new IndividualCredit(this.indivCreditType,
				this.getNameOfCredit(), this.getTypeOfCurrency(),
				this.getPercentage(), this.isActive(),
				this.getMaxSizeOfCredit(), this.isClosing(),
				this.isFixedPercentage());
	}

	public IndividualCreditType getTypeOfIndivCredit() {
		return indivCreditType;
	}

	public void setTypeOfIndivCredit(
			IndividualCreditType typeOfIndivCredit) {

		if (typeOfIndivCredit != null) {
			this.indivCreditType = typeOfIndivCredit;
		} else {
			//throw new InputTypeOfIndividualCreditIsNullException();
		}
	}

	@Override
	public String toString() {
		return "\nIndividualsCredit: " + super.toString()
				+ ", typeOfIndivCredit= " + indivCreditType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((indivCreditType == null) ? 0
				: indivCreditType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (getClass() != obj.getClass() || !super.equals(obj)) {
			return false;
		}

		IndividualCredit other = (IndividualCredit) obj;

		if (indivCreditType != other.indivCreditType) {
			return false;
		}

		return true;
	}
}
