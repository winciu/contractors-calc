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

    public WageCalculator(Country countryYouWorkIn, ExchangeRate exchangeRate) {
        this.country = countryYouWorkIn;
        this.exchangeRate = exchangeRate;
    }

    public Wage calculate(Money dayRate, GrossNetType dayRateType, int workingDaysInMonth) {
        Money dayRateGross = dayRate;
        if (GrossNetType.NET.equals(dayRateType)) {
            NetGrossCalculator netGrossCalculator = new NetGrossCalculator();
            final int percent = country.getEconomicFactors().getTaxRate();
            dayRateGross = dayRate.withAmount(netGrossCalculator.asGross(dayRate.getAmount(), percent));
        }
        final Money monthlyRate = dayRateGross.multipliedBy(workingDaysInMonth);
        final EconomicFactors economicFactors = country.getEconomicFactors();
        if (Objects.isNull(exchangeRate) || !isCurrencyConversionNeeded(dayRateGross)) {
            final Money fixedCostsInOriginalCurrency = Money.of(CurrencyUnit.of(country.getCurrency()),
                                                                economicFactors.getFixedCosts());
            return new Wage(monthlyRate, new WageMetadata(economicFactors.getTaxRate(), fixedCostsInOriginalCurrency));
        }
        final CurrencyUnit initialCurrency = CurrencyUnit.of(exchangeRate.getTargetCurrency());
        final Money fixedCostsInOriginalCurrency = Money.of(initialCurrency, economicFactors.getFixedCosts());
        WageMetadata wageMetadata = new WageMetadata(economicFactors.getTaxRate(), fixedCostsInOriginalCurrency);
        final Wage wageInOriginalCurrency = new Wage(monthlyRate, wageMetadata);

        return convertWageToSourceCurrency(wageInOriginalCurrency, exchangeRate);
    }

    private static Wage convertWageToSourceCurrency(Wage wageInOriginalCurrency, ExchangeRate exchangeRate) {
        final CurrencyCalculator currencyCalculator = new CurrencyCalculator();
        final Money grossValueInSourceCurrency = currencyCalculator.convert(wageInOriginalCurrency.getGrossValue(),
                                                                            exchangeRate);
        final Money fixedCostsInOriginalCurrency = wageInOriginalCurrency.getMetadata().getFixedCosts();
        final Money fixedCostsInSourceCurrency = currencyCalculator.convert(fixedCostsInOriginalCurrency, exchangeRate);
        WageMetadata wageMetadata = new WageMetadata(wageInOriginalCurrency.getMetadata().getTaxRate(),
                                                     fixedCostsInSourceCurrency);
        return new Wage(grossValueInSourceCurrency, wageMetadata);
    }

    private boolean isCurrencyConversionNeeded(Money dayRate) {
        return !dayRate.getCurrencyUnit().equals(CurrencyUnit.of(exchangeRate.getSourceCurrency()));
    }
}
