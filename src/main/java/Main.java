import com.google.gson.stream.JsonReader;
import connect.JSONHandler;
import connect.NBPConnector;
import gui.GUI;
import rates.Rate;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        NBPConnector connector = new NBPConnector("http://api.nbp.pl/api/exchangerates/tables/a/?format=json");
        JsonReader ratesReader = new JsonReader(new InputStreamReader(connector.getInputStream()));
        
        JSONHandler handler = new JSONHandler(ratesReader);
        List<Rate> rates = handler.convertJsonToRates();

        SwingUtilities.invokeLater(() -> new GUI(rates));
    }

}
