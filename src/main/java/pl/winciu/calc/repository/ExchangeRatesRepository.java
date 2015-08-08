package pl.winciu.calc.repository;

import org.springframework.data.repository.CrudRepository;
import pl.winciu.calc.model.ExchangeRate;

/**
 * @author Adam Winciorek
 */
public interface ExchangeRatesRepository extends CrudRepository<ExchangeRate, Long> {
}
