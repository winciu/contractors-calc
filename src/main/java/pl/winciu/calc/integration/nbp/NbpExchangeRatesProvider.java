package pl.winciu.calc.integration.nbp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.winciu.calc.integration.ExchangeRates;
import pl.winciu.calc.integration.ExchangeRatesProvider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Currency;
import java.util.Optional;

/**
 * @author Adam Winciorek
 */
@Component
public class NbpExchangeRatesProvider implements ExchangeRatesProvider {

    private static final Logger logger = LoggerFactory.getLogger(NbpExchangeRatesProvider.class);
    private static final String PROVIDER_NAME = "NBP";
    private final URI sourceUri;

    @Autowired
    public NbpExchangeRatesProvider(@Value("${nbp.exchg.rates.source.uri}") URI sourceUri) {
        this.sourceUri = sourceUri;
    }

    @Override public ExchangeRates obtainRates() {
        final Optional<ExchangeRatesTable> exchangeRatesTable = parseData();
        if (exchangeRatesTable.isPresent()) {
            return convertToOutputFormat(exchangeRatesTable.get());
        }
        return ExchangeRates.empty();
    }

    private ExchangeRates convertToOutputFormat(ExchangeRatesTable exchangeRatesTable) {
        ExchangeRates exchangeRates = new ExchangeRates(PROVIDER_NAME);
        for (ExchangeRatesTableItem item : exchangeRatesTable) {
            exchangeRates.addRate(Currency.getInstance(item.getCurrencyCode()), item.getBuyRate(), item.getUnit());
        }
        return exchangeRates;
    }

    private Optional<ExchangeRatesTable> parseData() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(ExchangeRatesTable.class);
            Unmarshaller um = ctx.createUnmarshaller();
            final ExchangeRatesTable ratesTable = (ExchangeRatesTable) um.unmarshal(sourceUri.toURL());
            return Optional.of(ratesTable);
        } catch (JAXBException e) {
            logger.error("Cannot parse exchange rates table", e);
            return Optional.empty();
        } catch (MalformedURLException e) {
            logger.error("Source URL '{}' is not valid", sourceUri,e);
            return Optional.empty();
        }
    }
}
