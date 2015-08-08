package pl.winciu.calc.api;

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

    @RequestMapping(value = "/{countryCode}/currency", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CurrencyRepresentation getCurrencyCode(@PathVariable String countryCode) {
        Country country = repository.findOne(countryCode);
        return new CurrencyRepresentation(country.getCurrencyCode());
    }




}
