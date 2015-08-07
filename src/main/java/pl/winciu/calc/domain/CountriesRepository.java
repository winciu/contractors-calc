package pl.winciu.calc.domain;

import pl.winciu.calc.domain.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Adam Winciorek
 */
public interface CountriesRepository extends PagingAndSortingRepository<Country, String>{

}
