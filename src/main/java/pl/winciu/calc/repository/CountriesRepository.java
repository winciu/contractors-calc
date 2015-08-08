package pl.winciu.calc.repository;

import pl.winciu.calc.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Adam Winciorek
 */
public interface CountriesRepository extends PagingAndSortingRepository<Country, String>{

}
