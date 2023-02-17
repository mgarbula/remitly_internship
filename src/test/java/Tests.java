import com.google.gson.stream.JsonReader;
import connect.JSONHandler;
import connect.NBPConnector;
import org.junit.Before;
import org.junit.Test;
import rates.Calculator;
import rates.Rate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tests {

    List<Rate> rates;

    @Before
    public void getRates() throws IOException {
        NBPConnector connector = new NBPConnector("http://api.nbp.pl/api/exchangerates/tables/a/2016-04-04/?format=json");
        JsonReader ratesReader = new JsonReader(new InputStreamReader(connector.getInputStream()));

        JSONHandler handler = new JSONHandler(ratesReader);
        rates = handler.convertJsonToRates();
    }

    @Test
    public void testCalculator() {
        Rate usd = null;
        for (Rate rate : rates) {
            if (rate.getCode().equals("USD")) {
                usd = rate;
                break;
            }
        }

        Calculator calculator = new Calculator(usd);
        assertEquals(3.7254, calculator.youSend(1.0), 0.01);
        assertEquals(0.2654, calculator.theyReceive(1.0), 0.01);
    }

    @Test
    public void testCorrectnessOfRates() {
        Rate usd = null;
        Rate czk = null;
        for (Rate rate : rates) {
            if (rate.getCode().equals("USD")) {
                usd = rate;
            } else if (rate.getCode().equals("CZK")) {
                czk = rate;
            }
        }

        assertEquals(usd.getCurrency(), "dolar amerykański");
        assertEquals(czk.getCurrency(), "korona czeska");
    }

}
