package by.epam.javaweb.voitenkov.task6.model.entity.intermed;

/**
 * @author Sergey Voitenkov March 16 2019
 */

public abstract class FinancialIntermediary {

	private int idNumberOfIntermediary;
	private String intermediaryName;
	private static final int DEFAULT_ID_NUMBER = -1;
	private static final String DEFAULT_INTERM_NAME = "Defaul Name";

	{
		idNumberOfIntermediary = DEFAULT_ID_NUMBER;
		intermediaryName = DEFAULT_INTERM_NAME;
	}

	public FinancialIntermediary() {
	}

	public FinancialIntermediary(int idNumberOfIntermediary,
			String nameOfIntermediary) {

		if (idNumberOfIntermediary > 0) {
			this.idNumberOfIntermediary = idNumberOfIntermediary;
		}

		if (nameOfIntermediary != null) {
			this.intermediaryName = nameOfIntermediary;
		}
	}

	public abstract FinancialIntermediary getCloneOfInstance();

	public int getIdNumberOfIntermediary() {
		return idNumberOfIntermediary;
	}

	public void setIdNumberOfIntermediary(int idNumberOfIntermediary){

		if (idNumberOfIntermediary > 0) {
			this.idNumberOfIntermediary = idNumberOfIntermediary;
		} else {
			//throw new NegativeValueOfIdException();
		}

	}

	public String getNameOfIntermediary() {
		return intermediaryName;
	}

	public void setNameOfIntermediary(String nameOfIntermediary){

		if (nameOfIntermediary != null) {
			this.intermediaryName = nameOfIntermediary;
		} else {
			//throw new InputNameOfIntermediaryIsNullException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNumberOfIntermediary;
		result = prime * result + ((intermediaryName == null) ? 0
				: intermediaryName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		FinancialIntermediary other = (FinancialIntermediary) obj;

		if (idNumberOfIntermediary != other.idNumberOfIntermediary) {
			return false;
		}

		if (intermediaryName == null) {
			if (other.intermediaryName != null) {
				return false;
			}
		} else if (!intermediaryName
				.equals(other.intermediaryName)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "\n[idNumberOfIntermediary: " + idNumberOfIntermediary
				+ ", nameOfIntermediary: " + intermediaryName + "]";
	}
}
