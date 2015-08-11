package pl.winciu.calc;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import pl.winciu.calc.model.Country;
import pl.winciu.calc.model.EconomicFactors;
import pl.winciu.calc.model.ExchangeRate;
import pl.winciu.calc.model.ExchangeRateId;
import pl.winciu.calc.service.GrossNetType;
import pl.winciu.calc.service.Wage;
import pl.winciu.calc.service.WageCalculator;

import java.util.Currency;
import java.util.Locale;

/**
 * @author Adam Winciorek
 */
public class WageCalculatorTest {
    private static final int TAX_RATE = 20;
    private static final Country COUNTRY_I_WORK_IN = new Country("PL", Currency.getInstance(Locale.GERMANY), new
            EconomicFactors(TAX_RATE, 600));
    private static final Currency PLN = Currency.getInstance("PLN");
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final ExchangeRate PLNEUR = new ExchangeRate(new ExchangeRateId(null, PLN, EUR), 1, 4.1446);
    private static final int WORKING_DAYS_IN_MONTH = 22;

    @Test
    public void shouldCalculateCorrectWageUsingGrossDayRateWithCurrencyExchange() {
        WageCalculator wageCalculator = new WageCalculator(COUNTRY_I_WORK_IN, PLNEUR);
        final Money dayRate = Money.of(CurrencyUnit.of(EUR), 500.4);
        final Wage wage = wageCalculator.calculate(dayRate, GrossNetType.GROSS, WORKING_DAYS_IN_MONTH);
        Assert.assertEquals(Money.of(CurrencyUnit.of(PLN), 45627.07), wage.getGrossValue());
    }

    @Test
    public void shouldCalculateCorrectWageUsingNetDayRateWithCurrencyExchange() {
        WageCalculator wageCalculator = new WageCalculator(COUNTRY_I_WORK_IN, PLNEUR);
        final Money dayRate = Money.of(CurrencyUnit.of(EUR), 417);
        final Wage wage = wageCalculator.calculate(dayRate, GrossNetType.NET, WORKING_DAYS_IN_MONTH);
        Assert.assertEquals(Money.of(CurrencyUnit.of(PLN), 45627.07), wage.getGrossValue());
    }

    @Test
    public void shouldCalculateCorrectWageUsingGrossDayRateWhenNoCurrencyExchangeNeeded() {
        WageCalculator wageCalculator = new WageCalculator(COUNTRY_I_WORK_IN, null);
        final Money dayRate = Money.of(CurrencyUnit.of(PLN), 500.4);
        final Wage wage = wageCalculator.calculate(dayRate, GrossNetType.GROSS, WORKING_DAYS_IN_MONTH);
        Assert.assertEquals(Money.of(CurrencyUnit.of(PLN), 11008.8), wage.getGrossValue());
    }

    @Test
    public void shouldCalculateCorrectWageUsingNetDayRateWhenNoCurrencyExchangeNeeded() {
        WageCalculator wageCalculator = new WageCalculator(COUNTRY_I_WORK_IN, null);
        final Money dayRate = Money.of(CurrencyUnit.of(PLN), 417);
        final Wage wage = wageCalculator.calculate(dayRate, GrossNetType.NET, WORKING_DAYS_IN_MONTH);
        Assert.assertEquals(Money.of(CurrencyUnit.of(PLN), 11008.8), wage.getGrossValue());
    }

}
