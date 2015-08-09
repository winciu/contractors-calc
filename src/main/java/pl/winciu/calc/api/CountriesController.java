package pl.winciu.calc.api;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import pl.winciu.calc.api.representation.CountriesRepresentation;
import pl.winciu.calc.api.representation.CountryRepresentation;
import pl.winciu.calc.api.representation.EconomicFactorRepresentation;
import pl.winciu.calc.model.EconomicFactors;
import pl.winciu.calc.repository.CountriesRepository;
import pl.winciu.calc.api.representation.CurrencyRepresentation;
import pl.winciu.calc.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Adam Winciorek
 */
@RestController
@RequestMapping("/api/countries")
public class CountriesController {

    private final CountriesRepository repository;

    @Autowired
    public CountriesController(CountriesRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CountriesRepresentation getAllCountries() {
        final Iterable<Country> countries = repository.findAll();
        CountriesRepresentation countryRepresentations = new CountriesRepresentation();
        for (Country country : countries) {
            final EconomicFactors economicFactors = country.getEconomicFactors();
            final Money fixedCosts = Money.of(CurrencyUnit.of(country.getCurrency()), economicFactors.getFixedCosts());
            EconomicFactorRepresentation economicFactor = new EconomicFactorRepresentation(economicFactors.getTaxRate(),
                                                                                           fixedCosts
            );
            CurrencyRepresentation currencyRepresentation = new CurrencyRepresentation(country.getCurrency());
            final CountryRepresentation countryRepresentation = new CountryRepresentation(country.getCode(),
                                                                                          currencyRepresentation,
                                                                                          economicFactor);
            countryRepresentations.add(countryRepresentation);
        }
        return countryRepresentations;
    }

}
