package pl.winciu.calc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.winciu.calc.model.ExchangeRate;
import pl.winciu.calc.model.ExchangeRateId;

import java.util.Currency;

/**
 * @author Adam Winciorek
 */
public interface ExchangeRatesRepository extends CrudRepository<ExchangeRate, ExchangeRateId> {

    /**
     * Since currently we have only one exchange rates provider, this query looks like it is now. In case more we
     * have data from more exchange rates providers, then this query should be modified to select max(rate.value).
     * It is max(rate.value) since usually currency rate which is used to do the wage calculation is the exchange
     * provider's buy rate.
     * @param sourceCurrency
     * @param targetCurrency
     * @return
     */
    @Query("SELECT rate FROM ExchangeRate rate WHERE rate.sourceCurrency = :sourceCurr AND rate.targetCurrency = " +
                   ":targetCurr")
    ExchangeRate findBestExchangeRate(@Param("sourceCurr") Currency sourceCurrency,
                                      @Param("targetCurr") Currency targetCurrency);
}
