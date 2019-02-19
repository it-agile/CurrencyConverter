package de.itagile;

public class Currency {
	private String code;
	private Double value;
	
	// Question: No Tests?

	public Currency(String code, Double value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public Double getValue() {
		return value;
	}
	
	// Question: Why there are not setters?

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return value + " " + code;
	}
}
