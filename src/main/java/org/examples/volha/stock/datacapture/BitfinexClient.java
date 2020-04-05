package org.examples.volha.stock.datacapture;

import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/* This class collects data from Bitfinex API */

public class BitfinexClient extends StockClient {
    public String productPair;
    public static boolean productsReceived = false;
    public static String productPairStatic = null;
    public static boolean bitfinexConnected = false;

    public BitfinexClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public BitfinexClient(URI serverURI) {
        super(serverURI);
    }

    public BitfinexClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Bitfinex opened connection");
        bitfinexConnected = true;
/*

            for (String product : products) {
                StringBuilder convertedName = new StringBuilder(product);
                convertedName.deleteCharAt(3);
                convertedName.insert(0,"t");
                String bitfinexProductID = convertedName.toString();

                mysock.BitfinexSubscription bitfinexSubscription = new BitfinexSubscription(bitfinexProductID);
                System.out.println("Bitfinex opened connection" + bitfinexProductID);
                send(String.valueOf(bitfinexSubscription.setSubscriptionString(bitfinexProductID)));
            }

 */

        if (productPairStatic != null) {
            BitfinexSubscription bitfinexSubscription = new BitfinexSubscription();
            send(bitfinexSubscription.makeSubscrString(productPairStatic));
            System.out.println("Bitfinex sent subscription" + productPairStatic);
        } else {
            System.out.println("Bitfinex Waiting for input products");
        }


    }

    @Override
    public void onMessage(String message) {

        System.out.println("!!!!!Bitfinex received: " + message);

        // BitfinexDataProcessor bitfinexDataProcessor = new BitfinexDataProcessor();
        // System.out.println("BitfinexProcessor created");
        // bitfinexDataProcessor.put(message);
        System.out.println("BitfinexProcessor put");

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Bitfinex connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
        // System.out.println("HashtableValues:");
        //    for (String h : bitfinexKeys) {
        //      System.out.println("Bitfinex Hashtable value " + h + " : " + bitfinexHashtable.get(h));
        //}
        productsReceived = false;

    }


    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

}











