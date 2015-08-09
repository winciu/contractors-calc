package pl.winciu.calc;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import pl.winciu.calc.model.Country;
import pl.winciu.calc.model.EconomicFactors;
import pl.winciu.calc.model.ExchangeRate;
import pl.winciu.calc.model.ExchangeRateId;
import pl.winciu.calc.service.Wage;
import pl.winciu.calc.service.WageCalculator;

import java.util.Currency;
import java.util.Locale;

/**
 * @author Adam Winciorek
 */
public class WageCalculatorTest {
    private static final Country COUNTRY_I_WORK_IN = new Country("PL", Currency.getInstance(Locale.GERMANY), new
            EconomicFactors(20, 600));
    private static final Currency PLN = Currency.getInstance("PLN");
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final ExchangeRate PLNEUR = new ExchangeRate(new ExchangeRateId(null, PLN, EUR), 1, 4.1446);
    private static final int WORKING_DAYS_IN_MONTH = 22;

    @Test
    public void shouldCalculateCorrectWageWithCurrencyExchange() {
        WageCalculator wageCalculator = new WageCalculator(COUNTRY_I_WORK_IN, PLNEUR);
        final Money dayRate = Money.of(CurrencyUnit.of(EUR), 500);
        final Wage wage = wageCalculator.calculate(dayRate, WORKING_DAYS_IN_MONTH);
        Assert.assertEquals(Money.of(CurrencyUnit.of(PLN), 45590.6), wage.getGrossValue());
    }

    @Test
    public void shouldCalculateCorrectWageWhenNoCurrencyExchangeNeeded() {
        WageCalculator wageCalculator = new WageCalculator(COUNTRY_I_WORK_IN, null);
        final Money dayRate = Money.of(CurrencyUnit.of(PLN), 500);
        final Wage wage = wageCalculator.calculate(dayRate, WORKING_DAYS_IN_MONTH);
        Assert.assertEquals(Money.of(CurrencyUnit.of(PLN), 11000.0), wage.getGrossValue());
    }

}
