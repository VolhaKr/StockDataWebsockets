package org.examples.volha.stock.webpresentation;

public class InputProducts {

    private String productPair;
    private String bitfinexProductPair;

    public InputProducts(String productPair) {
        this.productPair = productPair;
    }

    public InputProducts() {
    }

    public void setProductPair(String productPair) {
        this.productPair = productPair;
    }

    public String getBitfinexProductPair() {
        return bitfinexProductPair;

    }

    public String getProductPair() {

        return productPair;
    }


//    public void setBitfinexProductPair(String productPair) {
//
//        StringBuilder convertedName = new StringBuilder(productPair);
//        convertedName.deleteCharAt(3);
//        convertedName.insert(0, "t");
//        String bitfinexProductPair = convertedName.toString();
//    }

}