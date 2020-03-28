package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.database.DataSource;
import model.enums.Continent;
import model.interfaces.CountryDAO;


public class MysqlCountryDAO implements CountryDAO {

	//implementation of interface CountryDAO

	@Override
	public ArrayList<Country> getCountries() {
		// TODO Auto-generated method stub
		
		//Creating the array of countries and getting the instance of the database
		ArrayList<Country> countries = new ArrayList<Country>();
		DataSource source = DataSource.getInstance();
		
		
		
		try {
			ResultSet rs = source.select("SELECT * FROM country");
			while(rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				String continent = rs.getString(3);
				float sArea = rs.getFloat(4);
				String hState = rs.getString(5);
				
				//Using the builder pattern for creating a new country object
				Country country = new Country.CountryBuilder(code, name, Continent.getByName(continent))
						.setSurfaceArea(sArea)
						.setHeadOfState(hState)
						.build();
				
				countries.add(country);
			}
			
			return countries;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}catch(NullPointerException e) {
			
			System.out.println("Continent does not match with list of continents...");
			System.out.println("Stopping query.");
			System.out.println("");
			
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Country findCountryByCode(String code) {
		// TODO Auto-generated method stub
		DataSource source = DataSource.getInstance();
		
		String query = "SELECT * FROM country WHERE Code = '" + code + "';";
	
		Country country = null;
		
		try {
			ResultSet rs = source.select(query);
			if(rs.next()) {
				//Retrieving object attributes if found
				String name = rs.getString(2);
				String continent = rs.getString(3);
				float sArea = rs.getFloat(4);
				String hState = rs.getString(5);
				
				//Using the builder pattern for creating a new country object
				country = new Country.CountryBuilder(code, name, Continent.getByName(continent))
						.setSurfaceArea(sArea)
						.setHeadOfState(hState)
						.build();
				
			}
				//if not found it will return null because it has not been instantiated
				return country;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			//if something happened with the database it will return null
			return null;
		}

		
	}

	@Override
	public boolean saveCountry(Country country) {
		// TODO Auto-generated method stub
		
		//this method will save a country in the database
		DataSource source = DataSource.getInstance();
		
		String name = country.getName();
		String code = country.getCode();
		Continent continent = country.getContinent();
		float sArea = country.getSurfaceArea();
		String hState = country.getHeadOfState();
		
		String query = "INSERT INTO country(Code, Name, Continent, SurfaceArea, HeadOfState) "
				+ "VALUES ('"+ code +"','"+ name + "','" + continent.getName() + "'," +sArea+ ",'" +hState+ "');";
		
		return source.save(query);
		
	}

	@Override
	public ArrayList<Country> findCountryByName(String name) {
		// TODO Auto-generated method stub
		DataSource source = DataSource.getInstance();
		
		String query = "SELECT * FROM country WHERE Name = '" + name + "'";
		ResultSet rs = source.select(query);
		ArrayList<Country> countries = new ArrayList<Country>();
		Country country = null;
		
		try {
			while(rs.next()) {
				String code = rs.getString(1);
				String continent = rs.getString(3);
				float sArea = rs.getFloat(4);
				String hState = rs.getString(5);
				
				//Using the builder pattern for creating a new country object
				country = new Country.CountryBuilder(code, name, Continent.getByName(continent))
						.setSurfaceArea(sArea)
						.setHeadOfState(hState)
						.build();
				
				countries.add(country);
			}
			
			return countries;
		
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void closing() {
		
		//Closing the database instance
		DataSource.getInstance()
					.closing();
		
	}



}
