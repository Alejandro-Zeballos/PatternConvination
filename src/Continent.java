
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
	
	public String getName() {
		return name;
	}
	
	public static Continent getByName(String name) {
		for(Continent continent: values()) {
			if(continent.getName().equals(name)) {
				return continent;
			}		
		}
		
		throw new IllegalArgumentException(name + " is not a valid Continent");
	}
	
}
