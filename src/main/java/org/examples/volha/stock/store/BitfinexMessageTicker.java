package org.examples.volha.stock.store;

public class BitfinexMessageTicker {

    //The message received from bitfinex for subscription to a ticket:
    /*Bitfinex received: {"event":"subscribed","channel":"ticker","chanId":216337,"symbol":"tBTCUSD","pair":"BTCUSD"}*/

    private String event;
    private String channel;
    private int chanId;
    private String symbol;
    private String pair;

    public BitfinexMessageTicker(String event, String channel, int chanId, String symbol, String pair) {

        this.event = event;
        this.channel = channel;
        this.chanId = chanId;
        this.symbol = symbol;
        this.pair = pair;
    }


    public void setChanId(int chanId) {
        this.chanId = chanId;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


    public int getChanId() {
        return chanId;
    }

    public String getChannel() {
        return channel;
    }

    public String getPair() {
        return pair;
    }
}
