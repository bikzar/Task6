package by.epam.javaweb.voitenkov.task6.model.entity.credit;

import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CompanySizeType;
import by.epam.javaweb.voitenkov.task6.model.entity.entityenum.CurrencyType;

/**
 * @author Sergey Voitenkov March 16 2019
 */

public class CompanyCredit extends Credit {
	
	private static final long serialVersionUID = 6851012690753916399L;
	
	private CompanySizeType companySize = CompanySizeType.SMALL;

	public CompanyCredit() {
	}

	public CompanyCredit(CompanySizeType companySize,
			String creditName, CurrencyType currencyType,
			double percentage, boolean isActive,
			double maxSizeOfCredit, boolean isClosing,
			boolean isFixedPercentage) {

		super(creditName, currencyType, percentage, isActive,
				maxSizeOfCredit, isClosing, isFixedPercentage);

		if (companySize != null) {
			this.companySize = companySize;
		}
	}

	@Override
	public boolean extraEquals(Credit credit) {

		if (!super.extraEquals(credit)
				|| this.getClass() != credit.getClass()) {
			return false;
		}

		CompanyCredit companyCredit = (CompanyCredit) credit;

		if (companySize != companyCredit.companySize) {
			return false;
		}

		return true;
	}

	@Override
	public CompanyCredit getCloneOfCredit() {

		return new CompanyCredit(this.companySize,
				this.getNameOfCredit(), this.getTypeOfCurrency(),
				this.getPercentage(), this.isActive(),
				this.getMaxSizeOfCredit(), this.isClosing(),
				this.isFixedPercentage());
	}

	public CompanySizeType getSizeOfCompany() {
		return companySize;
	}

	public void setSizeOfCompany(CompanySizeType companySize)
			{

		if (companySize != null) {
			this.companySize = companySize;
		} else {
			//throw new InputSizeOfCompanyIsNullException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((companySize == null) ? 0
				: companySize.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (!super.equals(obj) || getClass() != obj.getClass()) {
			return false;
		}

		CompanyCredit other = (CompanyCredit) obj;

		if (companySize != other.companySize) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "\nCompanysCredit:" + super.toString()
				+ ", sizeOfCompany=" + companySize + "]";
	}
}
