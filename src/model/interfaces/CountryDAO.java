package model.interfaces;
import java.util.ArrayList;

import model.Country;

public interface CountryDAO {

	public ArrayList<Country> getCountries();
	public ArrayList<Country> findCountryByName(String name);
	public Country findCountryByCode(String code);
	public boolean saveCountry(Country country);
	public void closing();
	
}
