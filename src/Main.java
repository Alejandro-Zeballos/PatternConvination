import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	private CountryDAO dao;
	private ArrayList<Country> countries;;
	private Country country;
	private BufferedReader br;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initialize();
	}
	
	private static void initialize() {
		new Main();
	}
	
	private Main() {
		br = new BufferedReader(new InputStreamReader(System.in));
		dao = new MSQLSinglettonDAO();
		int option = 0;
		
		listOptions();
		
		try {
			option = getOption();
			runMenuOption(option);
		}catch(IOException e) {
			e.printStackTrace();
			initialize();
		}
		
		
		
		
	}
	
	public static void listOptions() {
		System.out.println("");
		System.out.println("------------------------o-----------------------");
		System.out.println("Choose one option to continue");
		System.out.println("");
		System.out.println("1) List all countries");
		System.out.println("2) Find a country by code");
		System.out.println("3) Find a country by name");
		System.out.println("4) Save a new country");
		System.out.println("5) Exit");
		
	}
	
	public void listContinentOptions() {
		System.out.println("");
		System.out.println("------------------------o-----------------------");
		System.out.println("Choose the continent");
		System.out.println("");
		System.out.println("1) Asia");
		System.out.println("2) Europe");
		System.out.println("3) North America");
		System.out.println("4) South America");
		System.out.println("5) Africa");
		System.out.println("6) Oceania");
		System.out.println("7) Antartica");
	}
	
	public void runMenuOption(int option) {
		String name;
		String code;
		Continent continent;
		float surfaceArea;
		String countryHead;
		
		switch(option) {
		case 1:
			countries = dao.getCountries();
			for(Country country: countries ) {
				System.out.println(country);
			}
			break;
			
		case 2:
		
			try {
				code = getInput("Input the country code");
			}catch(IOException e) {
				e.printStackTrace();
				break;
			}
			
			country = dao.findCountryByCode(code);
			System.out.println(country);
			break;
			
		case 3:
			
			try {
				name = getInput("Input the country name");
			}catch(IOException e) {
				e.printStackTrace();
				break;
			}
			
			country = dao.findCountryByName(name);
			System.out.println(country);
			break;
		
		case 4:
			
			try {
				name = getInput("Input the country name");
				code = getInput("Input the country code");
				//this line will present to the user the continents available and asks to choose one
				listContinentOptions();
				int Option = getOption();
				continent = getContinentEnum(Option);
				
				surfaceArea = (float) Double.parseDouble(getInput("Input the surface area of the country"));
				countryHead = getInput("Input the name of the president");
				
				Country.CountryBuilder builder = new Country.CountryBuilder(code, name, continent);
				
				//creating a new country object with the CountryBuilder
				country = new Country.CountryBuilder(code, name, continent)
						.setHeadOfState(countryHead)
						.setSurfaceArea(surfaceArea)
						.build();
				
			}catch(IOException e) {
				e.printStackTrace();
				initialize();
			}	
			
			//saving the country in the database
			dao.saveCountry(country);
			System.out.println("Saved correctly");
			System.out.println("");
			break;
		
		case 5:
			dao.closing();
			System.exit(0);
		
		default:
			System.out.println("Wrong option");
			break;
			
		}
		initialize();
	}
	
	public Continent getContinentEnum(int option) {
		
		switch(option) {
		case 1:
			return Continent.ASIA;
		case 2:
			return Continent.EUROPE;
		case 3:
			return Continent.N_AMERICA;
		case 4:
			return Continent.S_AMERICA;
		case 5:
			return Continent.AFRICA;
		case 6:
			return Continent.OCEANIA;
		case 7:
			return Continent.ANTARCTICA;
		case 0:
			System.out.println("Insert a valid continent option");
			break;
			
		}
		return null;
	}
	
	public String getInput(String message) throws IOException{
		
		System.out.println("");
		System.out.println(message);
		String input = br.readLine();
		return input;
		
	}
	
	public String getNameInput() throws IOException{
		
		System.out.println("");
		System.out.println("Input the country code");
		String code = br.readLine();
		return code;
		
	}

	public int getOption() throws IOException{
		
		
		String option = br.readLine().trim();
		
		//this line will try to convert the input to integer and if it fails means that the input is wrong 
		try {
			int numericOption = Integer.parseInt(option);
			return numericOption;
		}catch(Exception e) {
			System.out.println("Insert a valid option");
			
		}
		
		return 0;
	}

}
