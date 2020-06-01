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
    private final BitfinexSubscription bitfinexSubscription;
    // private final BitfinexDataProcessor bitfinexDataProcessor;
       // private final InputProducts inputProducts;
    @Autowired
    //public StockInfoController(BitfinexDataProcessor bitfinexDataProcessor, BitfinexSubscription bitfinexSubscription)
    public StockInfoController(BitfinexSubscription bitfinexSubscription)
    {
        this.bitfinexSubscription = bitfinexSubscription;
        //this.bitfinexDataProcessor = bitfinexDataProcessor;
        // this.inputProducts = inputProducts;
    }
    @MessageMapping("/products")
    @SendTo("/topic/stockdata")
    public StockInfo stockInfo(InputProducts products) throws Exception {
        //???? return???
        //bitfinexDataProcessor.init();
        Thread.sleep(1000); // simulated delay
        productPairStatic = products.getProductPair();
        System.out.println(" " + products.getProductPair());
        if (bitfinexConnected) {
            bitfinexClient.send(bitfinexSubscription.makeSubscrString(products.getProductPair()));
            System.out.println("Bitfinex connected and subsription sent " + products.getBitfinexProductPair());
        }
        try {
            // return new StockInfo( coinbaseClient.coinbaseHashtable.get(products.getProductPair()).getPrice());
            // return new StockInfo("Hello, " + HtmlUtils.htmlEscape(products.getProductPair()+ "!"));
            // return new StockInfo("Hello, " + "bitfinexDataProcessor works " +products.getBitfinexProductPair() + "!");
            System.out.println("   ++++++This should be returned  " + products.getProductPairWithoutSlash());
            String temp_productID;
            System.out.println("VERYIMPORTANT!!!!   !!!++++++This should be returned  " );
            temp_productID = products.getProductPairWithoutSlash();
                    //+ bitfinexDataProcessor.bitfinexProductIDDataObject.get(products.getProductPairWithoutSlash()).getPrice());
            bitfinexClient.bitfinexDataProcessor.printTest(temp_productID);
            System.out.println("   !!!++++++This should be returned  " + bitfinexClient.bitfinexDataProcessor.getPrice(temp_productID));
            return new StockInfo("Hello, " + bitfinexClient.bitfinexDataProcessor.getPrice(products.getProductPairWithoutSlash()) + "!");
            // return new StockInfo("Hello, " + products.getBitfinexProductPair() + "!");
        }
        /*    if ((bitfinexClient.bitfinexHashtable.get(BitfinexClient.productPairStatic)!=null)&(bitfinexClient.bitfinexHashtable.get(BitfinexClient.productPairStatic).getPrice()!=0)) {
                return new StockInfo("|       " + products.getProductPair() + "      |      " + products.getBitfinexProductPair() + "      |      " +
                        coinbaseClient.coinbaseHashtable.get(products.getProductPair()).getPrice() + "      |      " +
                        bitfinexClient.bitfinexHashtable.get(products.getBitfinexProductPair()).getPrice() + "      |      ");
            }
            else return  new StockInfo("|Wait a moment please");
        }*/ catch (Exception e) {
            return new StockInfo("Some problem occuried. Incorrect data " + e);

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
