
public class Country {
	
	private String code;
	private String name;
	private Continent continent;
	private float surfaceArea;
	private String headOfState;
	
	private Country(CountryBuilder builder) {
		this.code = builder.code;
		this.name = builder.name;
		this.continent = builder.continent;
		this.surfaceArea = builder.surfaceArea;
		this.headOfState = builder.headOfState;
	}
	
	@Override
	public String toString() {
		return "\tCountry name = " + this.name + "\n\t"
				+ "Code = " + this.code + "\n\t"
				+ "Continent = " + this.continent.getName() + "\n\t"
				+ "Surface Area = " + this.surfaceArea + "\n\t"
				+ "Head of State = " + this.headOfState + "\n";
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Continent getContinent() {
		return continent;
	}

	public float getSurfaceArea() {
		return surfaceArea;
	}

	public String getHeadOfState() {
		return headOfState;
	}
	
	public static class CountryBuilder{
		
		//Country builder
		private String code;
		private String name;
		private Continent continent;
		private float surfaceArea;
		private String headOfState;
		
		public CountryBuilder(String code, String name, Continent continent) {
			this.name = name;
			this.code = code;
			this.continent = continent;
			this.surfaceArea = 0;
			this.headOfState = "";
		}
		
		public CountryBuilder setSurfaceArea(float surfaceArea) {
			this.surfaceArea = surfaceArea;
			return this;
		}
		
		public CountryBuilder setHeadOfState(String headOfState) {
			this.headOfState = headOfState;
			return this;
		}
		
		public Country build() {
			return new Country(this);
		}
		
	}
	
	
	

}
