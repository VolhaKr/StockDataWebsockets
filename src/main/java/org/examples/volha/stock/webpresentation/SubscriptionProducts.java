package org.examples.volha.stock.webpresentation;

public class SubscriptionProducts {
    private String subscriptionProductPair;

    public SubscriptionProducts(String subscriptionProductPair) {
        this.subscriptionProductPair = subscriptionProductPair;
    }

    public SubscriptionProducts() {
    }

    public String getSubscriptionProductPair() {
        return subscriptionProductPair;
    }

    public void setSubscriptionProductPair(String subscriptionProductPair) {
        this.subscriptionProductPair = subscriptionProductPair;
    }
}
