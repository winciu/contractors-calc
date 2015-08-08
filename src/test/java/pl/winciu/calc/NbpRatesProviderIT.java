package pl.winciu.calc;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pl.winciu.calc.integration.ExchangeRates;
import pl.winciu.calc.integration.nbp.NbpExchangeRatesProvider;

import java.net.URI;

/**
 * @author Adam Winciorek
 */
public class NbpRatesProviderIT {

    @Test
    public void providerShouldCompleteSuccessfuly() {
        URI sourceUri = URI.create("http://www.nbp.pl/kursy/xml/LastC.xml");
        NbpExchangeRatesProvider provider = new NbpExchangeRatesProvider(sourceUri);
        final ExchangeRates exchangeRates = provider.obtainRates();
        Assert.assertThat(exchangeRates, Matchers.notNullValue());
        Assert.assertFalse(exchangeRates.isEmpty());
    }

}
