
public enum Continent {
	ASIA("Asia"),
	EUROPE("Europe"),
	N_AMERICA("North America"),
	S_AMERICA("South America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica");
	
	
	
	
	private Continent(final String name) {
		this.name = name;
	}
	
	private String name;
	
	//this method will return the name attribute of the enum
	public String getName() {
		return name;
	}
	
	//this method will return the Enum based on the name atribute of the enum
	public static Continent getByName(String name) {
		for(Continent continent: values()) {
			if(continent.getName().equals(name)) {
				return continent;
			}		
		}
		
		throw new IllegalArgumentException(name + " is not a valid Continent");
	}
	
}
