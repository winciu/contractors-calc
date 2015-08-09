package pl.winciu.calc.integration;

import pl.winciu.calc.model.ExchangeRate;
import pl.winciu.calc.model.ExchangeRateId;
import pl.winciu.calc.repository.ExchangeRatesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Winciorek
 */
public class ExchangeRatesRepositoryWriter {

    private final ExchangeRatesRepository repository;

    public ExchangeRatesRepositoryWriter(ExchangeRatesRepository repository) {
        this.repository = repository;
    }

    public void storeRates(ExchangeRates exchangeRates) {
        List<ExchangeRate> rates = convertToEntities(exchangeRates);
        repository.save(rates);
//        repository.findAll().forEach(System.err::println);
    }

    private List<ExchangeRate> convertToEntities(ExchangeRates exchangeRates) {
        List<ExchangeRate> output = new ArrayList<>();
        for (ExchangeRates.ExchangeRate exchangeRate : exchangeRates) {
            ExchangeRateId exchangeRateId = new ExchangeRateId(exchangeRates.getProviderName(),
                                                               exchangeRate.getSourceCode(),
                                                               exchangeRate.getTargetCode());
            final ExchangeRate rate = new ExchangeRate(exchangeRateId, exchangeRate.getUnits(),
                                                       exchangeRate.getValue().doubleValue());
            output.add(rate);
        }
        return output;
    }


}
