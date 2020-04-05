package org.examples.volha.stock.webpresentation;

import org.examples.volha.stock.datacapture.BitfinexClient;
import org.java_websocket.drafts.Draft_6455;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@ComponentScan(basePackages = {"org.examples.volha.stock.store", "org.examples.volha.stock.datacapture"})

//(scanBasePackages = {"org.examples.volha.stockdatacapture", "org.examples.volha.stockwebpresentation.webpresentation"})
public class WebPresentationApplication {

    //   StockInfoManager.main(args);
    //public static CoinbaseClient coinbaseClient;
    public static BitfinexClient bitfinexClient;

    public static void main(String[] args) {
        //CoinbaseClient coinbaseClient; // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
//        {
//            try {
//                CoinbaseClient coinbaseClient = new CoinbaseClient(new URI("wss://ws-feed.pro.coinbase.com"), new Draft_6455());
//                coinbaseClient.connect();
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//

        //   BitfinexClient bitfinexClient; // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts


        try {
            bitfinexClient = new BitfinexClient(new URI("wss://api-pub.bitfinex.com/ws/2"), new Draft_6455());
            bitfinexClient.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        //CoinbaseClient coinbaseClient = new CoinbaseClient(new URI("wss://ws-feed.pro.coinbase.com"));


        SpringApplication.run(WebPresentationApplication.class, args);

    }
}

