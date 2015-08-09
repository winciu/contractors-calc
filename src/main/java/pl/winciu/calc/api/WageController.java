package pl.winciu.calc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.winciu.calc.api.representation.WageMetadataRepresentation;
import pl.winciu.calc.api.representation.WageRepresentation;
import pl.winciu.calc.service.Wage;
import pl.winciu.calc.service.WageService;

import java.math.BigDecimal;

/**
 * @author Adam Winciorek
 */
@RestController
@RequestMapping("/api/wage")
public class WageController {

    private final WageService wageService;

    @Autowired
    public WageController(WageService wageService) {
        this.wageService = wageService;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<WageRepresentation> calculateWage(
            @RequestParam(value = "countryCode") String countryCode,
            @RequestParam(value = "dayRate") BigDecimal dayRate,
            @RequestParam(value = "exchgRateProvider", required = false) String providerName) {
        final Wage wage = wageService.calculateWage(countryCode, dayRate, providerName);
        if (wage == null) {
            return new ResponseEntity<>((WageRepresentation) null, HttpStatus.CONFLICT);
        }
        WageMetadataRepresentation metadata = new WageMetadataRepresentation(wage.calculateTaxAmount(),
                                                                             wage.getMetadata().getTaxRate(),
                                                                             wage.getMetadata().getFixedCosts());
        final WageRepresentation wageRepresentation = new WageRepresentation(wage.calculateNetIncome(),
                                                                             wage.getGrossValue(),
                                                                             wage.calculateNetValue(), metadata);
        return new ResponseEntity<>(wageRepresentation, HttpStatus.OK);
    }
}
