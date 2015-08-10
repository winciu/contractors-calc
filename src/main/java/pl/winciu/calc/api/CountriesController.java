package pl.winciu.calc.api;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import pl.winciu.calc.api.representation.CountriesRepresentation;
import pl.winciu.calc.api.representation.CountryRepresentation;
import pl.winciu.calc.api.representation.EconomicFactorRepresentation;
import pl.winciu.calc.model.EconomicFactors;
import pl.winciu.calc.repository.CountriesRepository;
import pl.winciu.calc.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Currency;
import java.util.Objects;

/**
 * @author Adam Winciorek
 */
@RestController
@RequestMapping("/api/countries")
public class CountriesController {
    private static final Logger logger = LoggerFactory.getLogger(CountriesController.class);

    private final CountriesRepository repository;

    @Autowired
    public CountriesController(CountriesRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CountriesRepresentation getAllCountries() {
        logger.debug("getting all countries ...");
        final Iterable<Country> countries = repository.findAll();
        CountriesRepresentation countryRepresentations = new CountriesRepresentation();
        for (Country country : countries) {
            final CountryRepresentation countryRepresentation = convertToRepresentation(country);
            countryRepresentations.add(countryRepresentation);
        }
        return countryRepresentations;
    }

    private CountryRepresentation convertToRepresentation(Country country) {
        final EconomicFactors economicFactors = country.getEconomicFactors();
        final Money fixedCosts = Money.of(CurrencyUnit.of(country.getCurrency()), economicFactors.getFixedCosts());
        EconomicFactorRepresentation economicFactor = new EconomicFactorRepresentation(economicFactors.getTaxRate(),
                                                                                       fixedCosts);
        return new CountryRepresentation(country.getCode(), country.getCurrency().getCurrencyCode(), economicFactor);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCountry(@RequestBody CountryRepresentation countryRepresentation,
                                        UriComponentsBuilder uriBuilder) {
        Country country = convertToEntity(countryRepresentation);
        final Country savedCountry = this.repository.save(country);
        URI uri = uriBuilder.path("/api/countries/{code}").buildAndExpand(savedCountry.getCode()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<Void>(null, headers, HttpStatus.CREATED);
    }

    private Country convertToEntity(CountryRepresentation country) {
        final EconomicFactorRepresentation factors = country.getEconomicFactors();
        final BigDecimal fixedCosts = factors.getFixedCosts().getAmount();
        EconomicFactors economicFactors = new EconomicFactors(factors.getTaxRate(), fixedCosts.doubleValue());
        return new Country(country.getCode(), Currency.getInstance(country.getCurrency().getCode()),
                           economicFactors);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryRepresentation> getCountryByCode(@PathVariable("code") String countryCode) {
        final Country country = repository.findOne(countryCode);
        if (Objects.isNull(country)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convertToRepresentation(country), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/codes", produces = MediaType.APPLICATION_JSON_VALUE)
    public CountriesRepresentation getCountriesCodeOnly() {
        String[] codes = repository.findCodesOnly();
        CountriesRepresentation countriesRepresentation = new CountriesRepresentation();
        for (String code : codes) {
            countriesRepresentation.add(new CountryRepresentation(code));
        }
        return countriesRepresentation;
    }

}
