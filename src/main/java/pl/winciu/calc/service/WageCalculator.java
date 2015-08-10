package pl.winciu.calc.service;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import pl.winciu.calc.model.Country;
import pl.winciu.calc.model.EconomicFactors;
import pl.winciu.calc.model.ExchangeRate;

import java.util.Objects;

/**
 * @author Adam Winciorek
 */
public class WageCalculator {

    private final Country country;
    private final ExchangeRate exchangeRate;
    private final CurrencyCalculator currencyCalculator;

    public WageCalculator(Country countryYouWorkIn, ExchangeRate exchangeRate) {
        this.country = countryYouWorkIn;
        this.exchangeRate = exchangeRate;
        this.currencyCalculator = new CurrencyCalculator();
    }

    public Wage calculate(Money dayRate, int workingDaysInMonth) {
        final Money monthlyRate = dayRate.multipliedBy(workingDaysInMonth);
        final EconomicFactors economicFactors = country.getEconomicFactors();
        if (Objects.isNull(exchangeRate) || !isCurrencyConversionNeeded(dayRate)) {
            final Money fixedCostsInOriginalCurrency = Money.of(CurrencyUnit.of(country.getCurrency()),
                                                                economicFactors.getFixedCosts());
            return new Wage(monthlyRate, new WageMetadata(economicFactors.getTaxRate(), fixedCostsInOriginalCurrency));
        }

        final Money grossValueInSourceCurrency = currencyCalculator.convert(monthlyRate, exchangeRate);
        final CurrencyUnit initialCurrency = CurrencyUnit.of(exchangeRate.getTargetCurrency());
        final Money fixedCostsInOriginalCurrency = Money.of(initialCurrency, economicFactors.getFixedCosts());
        final Money fixedCostsInSourceCurrency = currencyCalculator.convert(fixedCostsInOriginalCurrency, exchangeRate);
        WageMetadata wageMetadata = new WageMetadata(economicFactors.getTaxRate(), fixedCostsInSourceCurrency);
        return new Wage(grossValueInSourceCurrency, wageMetadata);
    }

    private boolean isCurrencyConversionNeeded(Money dayRate) {
        return !dayRate.getCurrencyUnit().equals(CurrencyUnit.of(exchangeRate.getSourceCurrency()));
    }
}
