import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MysqlDAO implements CountryDAO {

	

	@Override
	public ArrayList<Country> getCountries() {
		// TODO Auto-generated method stub
		
		//Creating the array of countries and getting the instance of the database
		ArrayList<Country> countries = new ArrayList<Country>();
		DataSourceSingleton source = DataSourceSingleton.getInstance();
		
		ResultSet rs = source.select("SELECT * FROM country");
		
		try {
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
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return countries;
	}

	@Override
	public Country findCountryByCode(String code) {
		// TODO Auto-generated method stub
		DataSourceSingleton source = DataSourceSingleton.getInstance();
		
		String query = "SELECT * FROM country WHERE Code = " + code;
		ResultSet rs = source.select(query);
		Country country = null;
		
		try {
			if(rs.next()) {
				String name = rs.getString(2);
				String continent = rs.getString(3);
				float sArea = rs.getFloat(4);
				String hState = rs.getString(5);
				
				//Using the builder pattern for creating a new country object
				country = new Country.CountryBuilder(code, name, Continent.getByName(continent))
						.setSurfaceArea(sArea)
						.setHeadOfState(hState)
						.build();
				
				return country;
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public boolean saveCountry(Country country) {
		// TODO Auto-generated method stub
		
		//this method will save a country in the database
		DataSourceSingleton source = DataSourceSingleton.getInstance();
		
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
	public Country findCountryByName(String name) {
		// TODO Auto-generated method stub
		DataSourceSingleton source = DataSourceSingleton.getInstance();
		
		String query = "SELECT * FROM country WHERE Name = '" + name + "'";
		ResultSet rs = source.select(query);
		Country country = null;
		
		try {
			if(rs.next()) {
				String code = rs.getString(1);
				String continent = rs.getString(3);
				float sArea = rs.getFloat(4);
				String hState = rs.getString(5);
				
				//Using the builder pattern for creating a new country object
				country = new Country.CountryBuilder(code, name, Continent.getByName(continent))
						.setSurfaceArea(sArea)
						.setHeadOfState(hState)
						.build();
	
				return country;
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public void closing() {
		
		//Closing the database instance
		DataSourceSingleton.getInstance()
						.closing();
		
	}



}
