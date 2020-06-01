package org.examples.volha.stock.store;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BitfinexDataProcessor {
    Set<Double> bitfinexConnectedChannelID;
    Hashtable<Double, String> bitfinexChannelIDProductID;
    Set<Double> keysBitfinexChannelIDProductID;
    public ConcurrentHashMap<String, BitfinexMessageProductData> bitfinexProductIDDataObject;
    Set<String> keysBitfinexProductIDDataObject;
//
//    public BitfinexDataProcessor() {
//    }
//

    public Set<Double> getBitfinexConnectedChannelID() {
        return bitfinexConnectedChannelID;
    }

    public void setBitfinexConnectedChannelID(Set<Double> bitfinexConnectedChannelID) {
        this.bitfinexConnectedChannelID = bitfinexConnectedChannelID;
    }
    public Hashtable<Double, String> getBitfinexChannelIDProductID() {
        return bitfinexChannelIDProductID;
    }
   public void setBitfinexChannelIDProductID(Hashtable<Double, String> bitfinexChannelIDProductID) {
        this.bitfinexChannelIDProductID = bitfinexChannelIDProductID;
    }
    public Set<Double> getKeysBitfinexChannelIDProductID() {
        return keysBitfinexChannelIDProductID;
    }
    public void setKeysBitfinexChannelIDProductID(Set<Double> keysBitfinexChannelIDProductID) {
        this.keysBitfinexChannelIDProductID = keysBitfinexChannelIDProductID;
    }

    public ConcurrentHashMap<String, BitfinexMessageProductData> getBitfinexProductIDDataObject() {
        return bitfinexProductIDDataObject;
    }

    public void setBitfinexProductIDDataObject(ConcurrentHashMap<String, BitfinexMessageProductData> bitfinexProductIDDataObject) {
        this.bitfinexProductIDDataObject = bitfinexProductIDDataObject;
    }

    public Set<String> getKeysBitfinexProductIDDataObject() {
        return keysBitfinexProductIDDataObject;
    }

    public void setKeysBitfinexProductIDDataObject(Set<String> keysBitfinexProductIDDataObject) {
        this.keysBitfinexProductIDDataObject = keysBitfinexProductIDDataObject;
    }

    public void init() {
        bitfinexConnectedChannelID = new HashSet<Double>();
        bitfinexChannelIDProductID = new Hashtable<Double, String>();
        keysBitfinexChannelIDProductID = bitfinexChannelIDProductID.keySet();
        bitfinexProductIDDataObject = new ConcurrentHashMap<String, BitfinexMessageProductData>();
        keysBitfinexProductIDDataObject = bitfinexProductIDDataObject.keySet();
    }

    private void putTicker(String message) {
        Gson g = new Gson();
        BitfinexMessageTicker bitfinexMessageTicker = g.fromJson(message, BitfinexMessageTicker.class);
        int receivedTickerChanID = bitfinexMessageTicker.getChanId();
        String receivedTickerSymbol = bitfinexMessageTicker.getPair();
        System.out.println("From BitfinexDataProcessor: to put received Bitfinex ChanID and pair:" + " ChanID: " + receivedTickerChanID + " pair: " + receivedTickerSymbol);
        //??? Add check for null
        //A set of connection channels is created
        if ((receivedTickerChanID != 0) & (receivedTickerSymbol != null)) {
            bitfinexConnectedChannelID.add(Double.valueOf(receivedTickerChanID));
            //fill hastable with channel-symbol from Bitfinex
            bitfinexChannelIDProductID.put(Double.valueOf(receivedTickerChanID), receivedTickerSymbol);
            System.out.println("From BitfinexDataProcessor: The following ChanID and pair was put into hashtable:" + " ChanID: " + receivedTickerChanID + " pair: " + receivedTickerSymbol);
        }
    }

    private void putProductData(String message) {
        // System.out.println("JSON Array message is received by the channel:  " + message);
        Gson gbt = new Gson();
        ArrayList bitfinexMessageArray                 //get Channel_ID from array message
                = (ArrayList) gbt.fromJson(message, List.class);
        Double receivedArrayChanId = (Double) bitfinexMessageArray.get(0);
        //get price from array message
        Double receivedArrayPrice = (Double) ((ArrayList) bitfinexMessageArray.get(1)).get(6);
        String receivedArrayProduct = bitfinexChannelIDProductID.get(receivedArrayChanId);
        if (receivedArrayProduct != null) {
            BitfinexMessageProductData bitfinexMessageProductData = new BitfinexMessageProductData(receivedArrayProduct, receivedArrayPrice);
            //BitfinexHashtableMessage bitfinexHashtableMessage = new BitfinexHashtableMessage(receivedArrayProduct, receivedArrayPrice);
            System.out.println(".....Bitfinex hashtable before putting" + bitfinexProductIDDataObject.toString());
            System.out.println("!!!Bitfinex message to put into hashtable " + receivedArrayProduct + receivedArrayPrice + String.valueOf((bitfinexMessageProductData).getPrice()));
            if (bitfinexProductIDDataObject.containsKey(receivedArrayProduct)) {
                bitfinexProductIDDataObject.replace(receivedArrayProduct, bitfinexMessageProductData);
            } else {
                bitfinexProductIDDataObject.put(receivedArrayProduct, bitfinexMessageProductData);
            }
            System.out.println(".....Bitfinex hashtable after putting" + bitfinexProductIDDataObject.toString());
            bitfinexProductIDDataObject.forEach((key1, value1) -> {
                String key = key1;
                BitfinexMessageProductData value = value1;
                Gson gson1 = new Gson();
                String jsonStr = gson1.toJson(value);

                System.out.println("...... Hashtable printing in cycle..."+ "Key: " + key + " Value: " + value);
                System.out.println("...... Hashtable printing in cycle...json..."+ "Key: " +key1+ jsonStr);
            });
            bitfinexProductIDDataObject.get("ETHUSD").getPrice();
            System.out.println("_______________________Hashtable gotten price"+ "Key: " +             bitfinexProductIDDataObject.get("ETHUSD").getPrice().toString());
            bitfinexProductIDDataObject.get(receivedArrayProduct).getSymbol();
            System.out.println("HERE Bitfinex hashtable is updated with: " + receivedArrayProduct + bitfinexMessageProductData.getPrice()+
                    "getPrice!!!!!! " + bitfinexProductIDDataObject.get("ETHUSD").getPrice()+
                    "this symbol is gotten - ---- "+  bitfinexProductIDDataObject.get(receivedArrayProduct).getSymbol());
        }

    }

    public void put(String message) {
        // Deserialization

        if (message.contains("ticker")) {
            putTicker(message);
            System.out.println("From BitfinexDataProcessor: ticker put");
        }

        // !!!!!to add check for uniqueness of a pair
        if (message.startsWith("[") & (!message.contains("hb"))) {
            putProductData(message);

            System.out.println("From BitfinexDataProcessor: productdata put");
        }
    }
public String printTest(String bitfinexProductPair){
        System.out.println("<>><<<>>><<>>bitfinexDataProcessor works " +bitfinexProductPair);
        return "bitfinexDataProcessor works " + bitfinexProductPair;
}

    public Double getPrice(String productPairWithoutSlash) {
        System.out.println("CALL TO GET PRICE "+" bitfinexProductPairWithoutSlash "+ productPairWithoutSlash);
        System.out.println("++++++from BitfinexDataProcessor:" + "bitfinexProductIDDataObject.get(bitfinexProductPairWithoutSlash) here" + bitfinexProductIDDataObject.get(productPairWithoutSlash));
        System.out.println("++++++from BitfinexDataProcessor:" +       "here bitfinexProductIDDataObject.get(bitfinexProductPair).getPrice() "+bitfinexProductIDDataObject.get(productPairWithoutSlash).getPrice());
        if ((bitfinexProductIDDataObject.get(productPairWithoutSlash) != null) & (bitfinexProductIDDataObject.get(productPairWithoutSlash).getPrice() != 0)) {
            Double bitfinexPrice;
            System.out.println(")))))))))))Product Pair " + bitfinexProductIDDataObject.get(productPairWithoutSlash));
            bitfinexPrice = bitfinexProductIDDataObject.get(productPairWithoutSlash).getPrice();
            System.out.println("((((((((((((from BitfinexDataProcessor!!!!Bitfinex price from hashtable"+ bitfinexPrice);
            return bitfinexPrice;
        } else {
            return null;
        }
    }

//    public String getPrice(String bitfinexProductPair) {
//        if ((bitfinexProductIDDataObject.get(bitfinexProductPair) != null) & (bitfinexProductIDDataObject.get(bitfinexProductPair).getPrice() != 0)) {
//            String outputString;
//            outputString = "|       " + bitfinexProductPair + "      |      " +
//
//                    bitfinexProductIDDataObject.get(bitfinexProductPair).getPrice() + "      |      ";
//            return outputString;
//        } else {
//            return null;
//        }
//    }

}

