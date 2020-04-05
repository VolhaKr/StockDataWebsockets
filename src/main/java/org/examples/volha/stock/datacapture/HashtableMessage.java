package org.examples.volha.stock.datacapture;

public class HashtableMessage {
    // private String BitfinexOrCoinbase; //"Bitfinex", "Coinbase"
    // to contain all the info in hashtables with <product_id, info>
    private String symbol; //product_id for Coinbase, pair for Bitfinex
    private String price;
    private String volume_24h; //Daily volume for both bases
    private String best_bid; //24 hours for Coinbase, Price of last highest bid for Bitfinex
    private String best_ask; //24 hours for Coinbase, Price of last highest bid for Bitfinex
    private String time;
    private String last_size;

    public HashtableMessage(String symbol, String price, String volume_24h, String best_bid, String best_ask, String time) {
        //   this.BitfinexOrCoinbase = BitfinexOrCoinbase;
        this.symbol = symbol;
        this.price = price;
        this.volume_24h = volume_24h;
        this.best_bid = best_bid;
        this.best_ask = best_ask;
        this.time = time;
    }


    public void setType(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(String product_id) {
        this.price = price;
    }

    public String getVolume_24h() {
        return volume_24h;
    }

    public String getPrice() {
        return price;
    }

    public String getBest_bid() {
        return best_bid;
    }

    public String getBest_ask() {
        return best_ask;
    }

    public String getTime() {
        return time;
    }


}
