package nl.hkstwk.spring6webclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void testListBeers() throws InterruptedException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeers().subscribe(response -> {
                    System.out.println(response);
                    atomicBoolean.set(true);
                }
        );

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListBeersMap() throws InterruptedException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeersMap().subscribe(response -> {
            System.out.println(response.toString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListBeersJsonNode() throws InterruptedException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}