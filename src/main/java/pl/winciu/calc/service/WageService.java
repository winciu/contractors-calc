package pl.winciu.calc.service;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.winciu.calc.model.Country;
import pl.winciu.calc.model.ExchangeRate;
import pl.winciu.calc.model.ExchangeRateId;
import pl.winciu.calc.repository.CountriesRepository;
import pl.winciu.calc.repository.ExchangeRatesRepository;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

/**
 * @author Adam Winciorek
 */
@Service
public class WageService {
    private static final Logger logger = LoggerFactory.getLogger(WageService.class);

    private final CountriesRepository countriesRepository;
    private final ExchangeRatesRepository exchangeRatesRepository;
    private final int workingDaysInMonth;

    @Autowired
    public WageService(CountriesRepository countriesRepository,
                       ExchangeRatesRepository exchangeRatesRepository,
                       @Value("${working.days.in.month}") int workingDaysInMonth) {
        this.countriesRepository = countriesRepository;
        this.exchangeRatesRepository = exchangeRatesRepository;
        this.workingDaysInMonth = workingDaysInMonth;
    }

    /**
     * @param countryOfOrigin           code of a country where a remuneration/salary is received
     * @param dayRate                   day rate in a currency of a  given country of origin
     * @param dayRateType               type of a given day rate (gross or net)
     * @param exchangeRatesProviderName provider name which exchange rates should be used while preforming calculation
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Wage calculateWage(String countryOfOrigin, BigDecimal dayRate, GrossNetType dayRateType,
                              String exchangeRatesProviderName) {
        final Country country = countriesRepository.findOne(countryOfOrigin);
        final Currency sourceCurrencyCode = Currency.getInstance(new Locale("pl", "PL"));
        final ExchangeRate exchangeRate = findExchangeRate(exchangeRatesProviderName, sourceCurrencyCode, country);
        return calculateWage(dayRate, dayRateType, country, exchangeRate);
    }

    private Wage calculateWage(BigDecimal dayRate, GrossNetType dayRateType, Country country,
                               ExchangeRate exchangeRate) {
        WageCalculator wageCalculator = new WageCalculator(country, exchangeRate);
        return wageCalculator.calculate(Money.of(CurrencyUnit.of(country.getCurrency()), dayRate), dayRateType,
                                        workingDaysInMonth);
    }

    private ExchangeRate findExchangeRate(String exchangeRatesProviderName, Currency sourceCurrency,
                                          Country country) {
        if (StringUtils.isEmpty(exchangeRatesProviderName)) {
            return exchangeRatesRepository.findBestExchangeRate(sourceCurrency, country.getCurrency());
        }
        final ExchangeRateId exchangeRateId = new ExchangeRateId(exchangeRatesProviderName, sourceCurrency,
                                                                 country.getCurrency());
        return exchangeRatesRepository.findOne(exchangeRateId);
    }
}
