package pl.winciu.calc;

import pl.winciu.calc.repository.CountriesRepository;
import pl.winciu.calc.model.Country;
import pl.winciu.calc.model.EconomicFactors;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Currency;
import java.util.Locale;

@SpringBootApplication
public class Application {

    @Bean
    InitializingBean init(CountriesRepository repository) {
        return () -> {
            repository.save(new Country("UK", Currency.getInstance(Locale.UK), new EconomicFactors(25, 600)));
            repository.save(new Country("DE", Currency.getInstance(Locale.GERMANY), new EconomicFactors(20, 800)));
            repository.save(new Country("PL", Currency.getInstance(new Locale("pl", "PL")), new EconomicFactors(19, 1200)));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
