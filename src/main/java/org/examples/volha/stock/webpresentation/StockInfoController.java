package org.examples.volha.stock.webpresentation;

import org.examples.volha.stock.datacapture.BitfinexSubscription;
import org.examples.volha.stock.store.BitfinexDataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static org.examples.volha.stock.datacapture.BitfinexClient.bitfinexConnected;
import static org.examples.volha.stock.datacapture.BitfinexClient.productPairStatic;
import static org.examples.volha.stock.webpresentation.WebPresentationApplication.bitfinexClient;

@Controller
public class StockInfoController {


//    @Autowired
//    public void setBitfinexDataProcessor(BitfinexDataProcessor bitfinexDataProcessor){
//        this.bitfinexDataProcessor = bitfinexDataProcessor;
//    }


    //    @Autowired
//    public void setBitfinexSubscription(BitfinexSubscription bitfinexSubscription){
//        this.bitfinexSubscription = bitfinexSubscription;
//    }
    private final BitfinexDataProcessor bitfinexDataProcessor;
    private final BitfinexSubscription bitfinexSubscription;

    @Autowired
    public StockInfoController(BitfinexDataProcessor bitfinexDataProcessor, BitfinexSubscription bitfinexSubscription) {
        this.bitfinexSubscription = bitfinexSubscription;
        this.bitfinexDataProcessor = bitfinexDataProcessor;
    }

    // @Autowired
    //BitfinexDataProcessor bitfinexDataProcessor;
//    @Autowired
//    BitfinexSubscription bitfinexSubscription;
////     BitfinexDataProcessor bitfinexDataProcessor;
////    @Autowired
////    BitfinexClient bitfinexClient;
//
//BitfinexDataProcessor bitfinexDataProcessor = new BitfinexDataProcessor();
    @MessageMapping("/products")
    @SendTo("/topic/stockdata")
    public StockInfo stockInfo(InputProducts products) throws Exception {
        bitfinexDataProcessor.init();


        Thread.sleep(1000); // simulated delay
        productPairStatic = products.getProductPair();
        if (bitfinexConnected) {
            bitfinexClient.send(bitfinexSubscription.makeSubscrString(products.getProductPair()));
            System.out.println("Bitfinex connected and subsription sent");
        }


        try {
            // return new StockInfo( coinbaseClient.coinbaseHashtable.get(products.getProductPair()).getPrice());
            // return new StockInfo("Hello, " + HtmlUtils.htmlEscape(products.getProductPair()+ "!"));
            return new StockInfo("Hello, " + bitfinexDataProcessor.getPrice(products.getBitfinexProductPair()) + "!");
        }
        /*    if ((bitfinexClient.bitfinexHashtable.get(BitfinexClient.productPairStatic)!=null)&(bitfinexClient.bitfinexHashtable.get(BitfinexClient.productPairStatic).getPrice()!=0)) {
                return new StockInfo("|       " + products.getProductPair() + "      |      " + products.getBitfinexProductPair() + "      |      " +
                        coinbaseClient.coinbaseHashtable.get(products.getProductPair()).getPrice() + "      |      " +
                        bitfinexClient.bitfinexHashtable.get(products.getBitfinexProductPair()).getPrice() + "      |      ");
            }
            else return  new StockInfo("|Wait a moment please");
        }*/ catch (Exception e) {
            return new StockInfo("Some problem occuried. Incorrect data");

        }

    }
//
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



/*
        Thread.sleep(1000); // simulated delay
        if (BitfinexClient.productsReceived == false) {
            System.out.println("BitfinexClient.productsReceived");
             BitfinexClient.productPairStatic = products.getBitfinexProductPair();
            System.out.println(BitfinexClient.productPairStatic+"test");
            }
            return new StockInfo("Hello, " + HtmlUtils.htmlEscape(products.getProductPair()) + "!");
        }
*/

}
