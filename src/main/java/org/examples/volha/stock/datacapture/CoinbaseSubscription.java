package org.examples.volha.stock.datacapture;

import com.google.gson.JsonObject;

public class CoinbaseSubscription {

        String type = "subscribe";
        String[] product_ids = {"ETH-USD", "ETH-EUR", "ETH-BTC", "BTC-USD"};
        String channels[] = {"ticker"};


        private String subscrPair;

        // private JsonObject subscriptionString = new JsonObject();
        public CoinbaseSubscription() {

        }

        public JsonObject setSubscriptionString(String subscrPair) {
                JsonObject subscriptionString = new JsonObject();
                product_ids [0] = subscrPair;
                subscriptionString.addProperty("event", "subscribe");
                subscriptionString.addProperty("channel", "ticker");
                subscriptionString.addProperty("symbol", subscrPair);
                return subscriptionString;
        }
}
