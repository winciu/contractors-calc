package pl.winciu.calc.integration.nbp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.winciu.calc.integration.ExchangeRatesRepositoryWriter;
import pl.winciu.calc.repository.ExchangeRatesRepository;

/**
 * @author Adam Winciorek
 */
@Component
public class NbpExchangeRatesDownloadTask {
    private static final Logger logger = LoggerFactory.getLogger(NbpExchangeRatesDownloadTask.class);

    private final NbpExchangeRatesProvider ratesProvider;
    private final ExchangeRatesRepository ratesRepository;

    @Autowired
    public NbpExchangeRatesDownloadTask(NbpExchangeRatesProvider ratesProvider, ExchangeRatesRepository ratesRepository) {
        this.ratesProvider = ratesProvider;
        this.ratesRepository = ratesRepository;
    }

    @Scheduled(cron = "${nbp.exchg.rates.download.task.cron}")
    public void download() {
        logger.info("Downloading NBP exchange rates ...");
        ExchangeRatesRepositoryWriter repositoryWriter = new ExchangeRatesRepositoryWriter(ratesRepository);
        repositoryWriter.storeRates(ratesProvider.obtainRates());
    }
}
