package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.enums.*;



public class UserInterface {

	BufferedReader br;
	
	public UserInterface() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void listOptions() {
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
	
	public Object getInput(TypeOfInput typeOfInput) {
		
		Object userInput = null;
		
		switch(typeOfInput) {
		
			case COUNTRY_NAME:
				userInput = getStringInput(typeOfInput.message());
				break;
			case CODE:
				userInput = getStringInput(typeOfInput.message(), 3);
				break;
			case HEAD_OF_STATE:
				userInput = getStringInput(typeOfInput.message());
				break;
			case NUMERIC:
				userInput = getNumericInput(typeOfInput.message());
				break;
			case SURFACE_AREA:
				userInput = getNumericInput(typeOfInput.message());
				break;
		}
		
		return userInput;
		
		
	}
	
	/*
	 * this method will be in charge of returning an option
	 * typed by the user
	 * 
	 */
	public int getOption(int initialOption, int finalOption){
		
		String option = "";
		int numericOption = 0;
		
		//this line will try to convert the input to integer and if it fails means that the input is wrong 
		try {
			option = br.readLine().trim();
			numericOption = Integer.parseInt(option);
		
		//this catch will hadle input related errors
		}catch(IOException e) {
			System.out.println("Somethin went wrong try again");
			return getOption(initialOption, finalOption);
			
		//this catch will be executed when the user does not input a numeric number
		}catch(NumberFormatException e) {
			System.out.println("Input a valid number");
			return getOption(initialOption, finalOption);
		}

		if(numericOption <= finalOption & numericOption >= initialOption) {
			return numericOption;
		}else {
			return getOption(initialOption, finalOption);
		}
		
	}
	
	/*
	 * this method will receive and option
	 * and will return an enum
	 * 
	 */
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

	//this method will only get and input from the user 
	//displaying a message and throwing an IOException
	//if something goes wrong
	
	private String getStringInput(String message) {

		System.out.println("");
		System.out.println(message);
		
		String input = "";
		
		try {
			
			input = br.readLine();
		
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("");
			System.out.println("Something went wrong please try again");
			
			//Recursive method that will get the correct input
			return(getStringInput(message));
		}
		return input;
	}
	
	/*
	 * this method will retrieve an input from the user with
	 * length = length
	 */
	private String getStringInput(String message, int length) {

		System.out.println("");
		System.out.println(message);
		
		String input = "";
		
		try {
			
			input = br.readLine();
			if(input.length() != length) {
				System.out.println("");
				System.out.println("Please enter exactly " + length + " characters");
				
				//Recursive method that will get the correct input
				return(getStringInput(message, length));
			}
		
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("");
			System.out.println("Something went wrong please try again");
			
			//Recursive method that will get the correct input
			return(getStringInput(message, length));
		}
		return input;
	}
	
	private int getNumericInput(String message) {

		
		//this method will only get a numeric input from the user 
		//displaying a message and throwing an exception
		//if something goes wrong
		System.out.println("");
		System.out.println(message);
		
		String input = "";
		int numericInput = 0;
		
		//this try catch will handle any IOException or NumericFormatException
		try {
			
			input = br.readLine(); 
			numericInput = Integer.parseInt(input);
		
			//this catch will execute when something went wrong with the buffered reader
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("");
			System.out.println("Something went wrong please try again");
			
			//Recursive method that will get the correct input
			return(getNumericInput(message));
			
			//this catch will execute when the input was not numeric
		}catch(NumberFormatException e) {
			e.printStackTrace();
			System.out.println("");
			System.out.println("Please enter a valid number");
			
			//Recursive method that will get the correct input
			return(getNumericInput(message));
		}
		return numericInput;
	}
	
	public void printMessage(String message) {
		System.out.println(message);
		System.out.println("");
	}

	public void close() {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
}
