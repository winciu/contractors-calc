package pl.winciu.calc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.winciu.calc.api.CountriesController;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.winciu.calc.model.Country;
import pl.winciu.calc.model.EconomicFactors;
import pl.winciu.calc.repository.CountriesRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CountriesControllerTest {

    private MockMvc mvc;
    @Mock
    private CountriesRepository countriesRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(new CountriesController(countriesRepository)).build();
    }

    @Test
    public void getCurrencyByCountryCode() throws Exception {
        Country country = new Country("PL", Currency.getInstance("PLN"), null);
        Mockito.when(countriesRepository.findOne(Mockito.anyString())).thenReturn(country);
        mvc.perform(MockMvcRequestBuilders.get("/api/countries/PL/currency").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string("{\"code\":\"PLN\"}"));
    }

    @Test
    public void shouldReturnAllAvailableCountries() throws Exception {
        Country country = new Country("PL", Currency.getInstance("PLN"),
                                      new EconomicFactors(19, 100));
        Country country2 = new Country("GB", Currency.getInstance("GBP"),
                                       new EconomicFactors(18, 700));
        Country country3 = new Country("DE", Currency.getInstance("EUR"),
                                       new EconomicFactors(20, 500));
        Mockito.when(countriesRepository.findAll()).thenReturn(Arrays.asList(country, country2, country3));
        mvc.perform(MockMvcRequestBuilders.get("/api/countries").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
        //                .andExpect(content());
    }

}
