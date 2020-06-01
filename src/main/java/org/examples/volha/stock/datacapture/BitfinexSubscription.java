package org.examples.volha.stock.datacapture;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class BitfinexSubscription {

    // creates JSON subscription string "{\"event\" : \"subscribe\", \"channel\": \"ticker\", \"symbol\": \"tETHEUR\"}"
    // for ETH-EUR subscription pair
    //String subscrString;

    private String bitfinexSubscrPair;
    private String subscrString;

    public BitfinexSubscription() {
    }

    public BitfinexSubscription(String bitfinexSubscrPair) {
        this.bitfinexSubscrPair = bitfinexSubscrPair;
    }


    public String getBitfinexSubscrPair() {
        return bitfinexSubscrPair;
    }

    public void setBitfinexSubscrPair(String bitfinexSubscrPair) {
        this.bitfinexSubscrPair = bitfinexSubscrPair;
    }

    public String getSubscrString() {

        return subscrString;
    }

    public void setSubscrString(String subscrString) {
        this.subscrString = subscrString;
    }

    public String makeSubscrString(String productPair) {
        String subscrString = String.valueOf(makeSubscriptionString(productPair));
        return subscrString;
    }
    // private JsonObject subscriptionString = new JsonObject();

    private JsonObject makeSubscriptionString(String productPair) {
        bitfinexSubscrPair = convertProductPairToBitfinex(productPair);
        JsonObject subscriptionString = new JsonObject();
        subscriptionString.addProperty("event", "subscribe");
        subscriptionString.addProperty("channel", "ticker");
        subscriptionString.addProperty("symbol", bitfinexSubscrPair);
        System.out.println("String for Bitfinex to open connection" + bitfinexSubscrPair);
        return subscriptionString;
    }

    private String convertProductPairToBitfinex(String subscrPair) {
        StringBuilder convertedName = new StringBuilder(subscrPair);
        convertedName.deleteCharAt(3);
        convertedName.insert(0, "t");
        String bitfinexProductPair = convertedName.toString();
        return bitfinexProductPair;
    }

}