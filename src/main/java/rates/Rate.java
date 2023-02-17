package rates;

public class Rate {

    private String currency;
    private String code;
    private Double mid;

    public Rate(String currency, String code, Double bid) {
        this.currency = currency;
        this.code = code;
        this.mid = bid;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public Double getMid() {
        return mid;
    }

    @Override
    public String toString() {
        return "currency: " + currency + "; code: " + code + "; bid: " + mid;
    }
}
