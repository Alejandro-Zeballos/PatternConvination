package controller;
import java.util.ArrayList;

import model.Country;

import model.enums.*;
import model.MysqlCountryDAO;
import model.interfaces.CountryDAO;
import view.UserInterface;

public class CountryRecoverySystem {
	
	//Data Access Object dao
	private CountryDAO dao;
	
	//array of countries
	private ArrayList<Country> countries;
	private Country country;
	private UserInterface console;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initialize();
	}
	
	private static void initialize() {
		new CountryRecoverySystem();
	}
	
	private CountryRecoverySystem() {
		
		//Initializing bufferedReader and DataAccessObject
		
		dao = new MysqlCountryDAO();
		console = new UserInterface();

		int option = 0;
		
		//Displaying options to the user
		console.listOptions();
		
		//Recovering option off the user between 1 - 5
		option = console.getOption(1, 5);
		
		//Option Listener
		runMenuOption(option);		
		
	}
	
	public void runMenuOption(int option) {
		
		//this method will be an option listener, running a 
		//chunk of code depending on the typed option. 
		String name;
		String code;
		Continent continent;
		float surfaceArea;
		String countryHead;
		
		switch(option) {
		
		case 1:
			countries = dao.getCountries();
			for(Country country: countries ) {
				console.printMessage(country.toString());
			}
			break;
			
		case 2:
			//this part will validate and give me the country code
			code = (String) console.getInput(TypeOfInput.CODE);
			
			//Searching country by code
			country = dao.findCountryByCode(code);

			//printing in the user interface
			if(country != null) {
				console.printMessage(country.toString());
			}else{
				console.printMessage("Country not found");
			}
			break;
		case 3:
			//this part will validate and retrieve the country name
			name = (String) console.getInput(TypeOfInput.COUNTRY_NAME);
			
			//Searching countries by name
			countries = dao.findCountryByName(name);
			
			//Displaying countries found
			if(countries != null) {
				for(Country country: countries ) {
					console.printMessage(country.toString());
				}
			}else {
				console.printMessage("Country not found");
			}
			
			break;
		
		case 4:
	
			//Getting inputs of name and code from the user
			name = (String) console.getInput(TypeOfInput.COUNTRY_NAME);
			code = (String) console.getInput(TypeOfInput.CODE);
				
			//this line will present to the user the continents available and asks to choose one
			console.listContinentOptions();
			
			//Retrieving the user option selected between 1-7
			int Option = console.getOption(1, 7);
			
			//converting the option selected to continent ENUM
			continent = console.getContinentEnum(Option);
				
			//geting the surface area input
			surfaceArea = (int) console.getInput(TypeOfInput.SURFACE_AREA);
			
			//Getting the president name
			countryHead = (String) console.getInput(TypeOfInput.HEAD_OF_STATE);
				
			//creating a new country object with the CountryBuilder
			country = new Country.CountryBuilder(code, name, continent)
					.setHeadOfState(countryHead)
					.setSurfaceArea(surfaceArea)
					.build();
			
			//saving the country in the database
			console.printMessage(country.toString());
			
			if(dao.saveCountry(country) == true) {
				//displaying ok message
				console.printMessage("Saved Correctly");
			}else {
				console.printMessage("Country not saved, could not connect "
									+ "to database try later");
			}
			
			break;
		
		case 5:
			//closing database
			dao.closing();
			
			//closing interface
			console.close();
			
			//closing program
			System.exit(0);
		
		default:
			//if the number the user inserted is not in the options then it will tell so and break off the switch
			System.out.println("Wrong option");
			break;
		}
		
		initialize();
	}
	
	
	
	

	

}
