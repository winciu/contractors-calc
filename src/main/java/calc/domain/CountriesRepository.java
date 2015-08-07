package calc.domain;

import calc.domain.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Adam Winciorek
 */
public interface CountriesRepository extends PagingAndSortingRepository<Country, String>{

}
