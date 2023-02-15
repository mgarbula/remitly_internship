package connect;

import com.google.gson.stream.JsonReader;
import rates.Rate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {

    private JsonReader ratesReader;

    public JSONHandler(JsonReader ratesReader) {
        this.ratesReader = ratesReader;
    }

    public List<Rate> convertJsonToRates() throws IOException {
        List<Rate> rates = new ArrayList<>();

        ratesReader.beginArray();
        ratesReader.beginObject();
        while (ratesReader.hasNext()) {
            String name = ratesReader.nextName();
            if (name.equals("rates")) {
                rates = getRates(ratesReader);
                break;
            } else {
                ratesReader.nextString();
            }
        }
        ratesReader.endObject();
        ratesReader.endArray();

        return rates;
    }

    private List<Rate> getRates(JsonReader ratesReader) throws IOException {
        List<Rate> rates = new ArrayList<>();

        ratesReader.beginArray();
        while (ratesReader.hasNext()) {
            rates.add(readRate(ratesReader));
        }
        ratesReader.endArray();

        return rates;
    }

    private Rate readRate(JsonReader ratesReader) throws IOException {
        String currency = null;
        String code = null;
        Double bid = 0.0;
        Double ask = 0.0;

        ratesReader.beginObject();
        while (ratesReader.hasNext()) {
            String name = ratesReader.nextName();
            if (name.equals("currency")) {
                currency = ratesReader.nextString();
            } else if (name.equals("code")) {
                code = ratesReader.nextString();
            } else if (name.equals("bid")) {
                bid = ratesReader.nextDouble();
            } else if (name.equals("ask")) {
                ask = ratesReader.nextDouble();
            }
        }
        ratesReader.endObject();

        return new Rate(currency, code, bid, ask);
    }

}
