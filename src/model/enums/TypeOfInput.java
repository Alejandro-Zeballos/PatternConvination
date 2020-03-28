package model.enums;

public enum TypeOfInput {
	CODE("Please enter the 3 digits code"),
	COUNTRY_NAME("Please enter the country name"), 
	NUMERIC_OPTION("Please select an option"), 
	NUMERIC("Please enter a number"),
	SURFACE_AREA("Please enter the surface area"),
	HEAD_OF_STATE("Please enter the president name");
	
	private final String message;
	
	TypeOfInput(String message) {
		this.message = message;
	}
	
	public String message() {
		return message;
	}
}
