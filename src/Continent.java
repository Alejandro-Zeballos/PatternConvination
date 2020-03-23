
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
	
}
