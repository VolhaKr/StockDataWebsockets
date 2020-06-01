package org.examples.volha.stock.store;

public class BitfinexMessageProductData {

    //// Trading pairs
    //[
    //  CHANNEL_ID,
    //  [
    //    BID,
    //    BID_SIZE,
    //    ASK,
    //    ASK_SIZE,
    //    DAILY_CHANGE,
    //    DAILY_CHANGE_RELATIVE,
    //    LAST_PRICE,
    //    VOLUME,
    //    HIGH,
    //    LOW
    //  ]
    //]
    private String symbol; //symbol: 'tBTCUSD'
    private Double price;

    public BitfinexMessageProductData(String symbol, Double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
