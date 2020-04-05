package org.examples.volha.stock.store;

public class CoinbaseMessage {
    //class for messages with info from Coinbase to be put into hashtable
    // received: {"type":"ticker","sequence":7375785613,"product_id":"ETH-USD","price":"182","open_24h":"172.77000000","volume_24h":"96619.63749365","low_24h":"167.50000000","high_24h":"182.40000000","volume_30d":"2981988.67872101","best_bid":"181.99","best_ask":"182.00","side":"buy","time":"2019-10-07T18:00:32.368000Z","trade_id":51769874,"last_size":"0.02750000"}
    private String type;
    private float sequence;
    private String product_id;
    private String price;
    private String open_24h;
    private String volume_24h;
    private String low_24h;
    private String high_24h;
    private String volume_30d;
    private String best_bid;
    private String best_ask;
    private String side;
    private String time;
    private String trade_id;
    private String last_size;

    public CoinbaseMessage(String type, float sequence, String product_id, String price, String open_24h, String volume_24h, String low_24h, String high_24h, String volume_30d, String best_bid, String best_ask, String side, String time, String trade_id, String last_size) {
        this.type = type;
        this.sequence = sequence;
        this.product_id = product_id;
        this.price = price;
        this.open_24h = open_24h;
        this.volume_24h = volume_24h;
        this.low_24h = low_24h;
        this.high_24h = high_24h;
        this.volume_30d = volume_30d;
        this.best_bid = best_bid;
        this.best_ask = best_ask;
        this.side = side;
        this.time = time;
        this.trade_id = trade_id;
        this.last_size = last_size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getType() {
        return type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getPrice() {
        return price;
    }

    public String getVolume_24h() {
        return volume_24h;
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
