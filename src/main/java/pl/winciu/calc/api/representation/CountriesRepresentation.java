package pl.winciu.calc.api.representation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Winciorek
 */
public class CountriesRepresentation {
    private final List<CountryRepresentation> countries;

    public CountriesRepresentation() {
        countries = new ArrayList<>();
    }

    public void add(CountryRepresentation representation) {
        countries.add(representation);
    }

    public List<CountryRepresentation> getCountries() {
        return countries;
    }
}
