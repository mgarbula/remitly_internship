package rates;

public class Calculator {

    private final Rate rate;

    public Calculator(Rate rate) {
        this.rate = rate;
    }

    public Double youSend(Double myMoney) {
        return myMoney * rate.getMid();
    }

    public Double theyReceive(Double theyMoney) {
        return theyMoney/rate.getMid();
    }

}
