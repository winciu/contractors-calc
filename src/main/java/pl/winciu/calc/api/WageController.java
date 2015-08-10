package pl.winciu.calc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pl.winciu.calc.api.representation.ErrorRepresentation;
import pl.winciu.calc.api.representation.WageMetadataRepresentation;
import pl.winciu.calc.api.representation.WageRepresentation;
import pl.winciu.calc.service.Wage;
import pl.winciu.calc.service.WageService;

import java.math.BigDecimal;
import java.util.Objects;

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
    public @ResponseBody ResponseEntity<?> calculateWage(
            @RequestParam(value = "countryCode") String countryCode,
            @RequestParam(value = "dayRate") String dayRate,
            @RequestParam(value = "exchgRateProvider", required = false) String providerName) {
        final ErrorRepresentation error = validateRequest(countryCode, dayRate);
        if (Objects.nonNull(error)) {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        final Wage wage;
        try {
            wage = wageService.calculateWage(countryCode, new BigDecimal(dayRate), providerName);
        } catch (Exception e) {
            return new ResponseEntity<Object>(new ErrorRepresentation(e.getLocalizedMessage()),
                                              HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (wage == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        WageMetadataRepresentation metadata = new WageMetadataRepresentation(wage.calculateTaxAmount(),
                                                                             wage.getMetadata().getTaxRate(),
                                                                             wage.getMetadata().getFixedCosts());
        final WageRepresentation wageRepresentation = new WageRepresentation(wage.calculateNetIncome(),
                                                                             wage.getGrossValue(),
                                                                             wage.calculateNetValue(), metadata);
        return new ResponseEntity<>(wageRepresentation, HttpStatus.OK);
    }

    private ErrorRepresentation validateRequest(@RequestParam(value = "countryCode") String countryCode,
                                                @RequestParam(value = "dayRate") String dayRate) {
        String message = null;
        if (StringUtils.isEmpty(countryCode)) {
            message = "Country is not specified";
        }else {
            try {
                NumberUtils.parseNumber(dayRate, BigDecimal.class);
            } catch (IllegalArgumentException e) {
                message = String.format("'%s' is not a valid number", dayRate);
            }
        }
        if (StringUtils.isEmpty(dayRate)) {
            message = "Day rate is not specified";
        }
        if (Objects.isNull(message)) {
            return null;
        }
        return new ErrorRepresentation(message);
    }
}
