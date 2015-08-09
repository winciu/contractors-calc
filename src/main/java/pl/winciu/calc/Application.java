package pl.winciu.calc;

import org.springframework.scheduling.annotation.EnableScheduling;
import pl.winciu.calc.integration.nbp.NbpExchangeRatesDownloadTask;
import pl.winciu.calc.repository.CountriesRepository;
import pl.winciu.calc.model.Country;
import pl.winciu.calc.model.EconomicFactors;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@SpringBootApplication
@EnableScheduling
public class Application {

    /**
     * Since the following tasks are scheduled using cron, we need to invoke them manually when the application
     * starts in order to fetch data (exchange rates). Otherwise, we will end up with an empty database just after
     * application starts.
     *
     * @param task task to download exchange rates
     * @return initialize data in DB
     */
    @Bean InitializingBean populateExchangeRates(NbpExchangeRatesDownloadTask task) {
        return task::download;
    }

    @Bean InitializingBean populateCountries(CountriesRepository repository) {
        return () -> {
            repository.save(new Country("UK", Currency.getInstance(Locale.UK), new EconomicFactors(25, 600)));
            repository.save(new Country("DE", Currency.getInstance(Locale.GERMANY), new EconomicFactors(20, 800)));
            repository.save(new Country("PL", Currency.getInstance(new Locale("pl", "PL")),
                                        new EconomicFactors(19, 1200)));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
