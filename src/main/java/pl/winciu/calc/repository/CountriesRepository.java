package pl.winciu.calc.repository;

import org.springframework.data.jpa.repository.Query;
import pl.winciu.calc.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Adam Winciorek
 */
public interface CountriesRepository extends PagingAndSortingRepository<Country, String>{

    @Query("SELECT c.code from Country c")
    String[] findCodesOnly();
}
