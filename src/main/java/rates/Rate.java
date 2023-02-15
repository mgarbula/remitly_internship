package rates;

public class Rate {

    private String currency;
    private String code;
    private Double bid;
    private Double ask;

    public Rate(String currency, String code, Double bid, Double ask) {
        this.currency = currency;
        this.code = code;
        this.bid = bid;
        this.ask = ask;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    @Override
    public String toString() {
        return "currency: " + currency + "; code: " + code + "; bid: " + bid + "; ask: " + ask;
    }
}
