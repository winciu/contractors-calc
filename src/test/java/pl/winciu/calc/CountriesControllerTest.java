package pl.winciu.calc;

import pl.winciu.calc.domain.api.CountriesController;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CountriesControllerTest {

    private MockMvc mvc;

//    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new CountriesController(null)).build();
    }

//    @Test
    public void getCurrencyByCountryCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/countries/PL/currency").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().node(equalTo("PLN")));
    }

}
