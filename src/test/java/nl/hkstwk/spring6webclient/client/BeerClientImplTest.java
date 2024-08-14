package nl.hkstwk.spring6webclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void listBeers() throws InterruptedException {
        beerClient.listBeers().subscribe(System.out::println);

        Thread.sleep(1000L);
    }
}