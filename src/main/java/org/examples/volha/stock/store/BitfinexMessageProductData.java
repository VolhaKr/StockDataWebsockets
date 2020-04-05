package org.examples.volha.stock.store;

public class BitfinexMessageProductData {

    // private String BitfinexOrCoinbase; //"Bitfinex", "Coinbase"
    // to contain all the info in hashtables with <product_id, info>
    private String symbol; //product_id for Coinbase, pair for Bitfinex
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
