package nl.hkstwk.spring6webclient.client;

import nl.hkstwk.spring6webclient.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void testListBeers() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeers().subscribe(response -> {
                    System.out.println(response);
                    atomicBoolean.set(true);
                }
        );

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListBeersMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeersMap().subscribe(response -> {
            System.out.println(response.toString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListBeersJsonNode() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListBeersDTO() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeersDto().subscribe(beerDto -> {
            System.out.println(beerDto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerById() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeersDto()
                .flatMap(dto -> beerClient.getBeerById(dto.getId()))
                .subscribe(dtoById -> {
                    System.out.println(dtoById.getBeerName());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByStyle() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.getBeerByStyle("Pale Ale")
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testCreateBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO newDTO = BeerDTO.builder()
                .beerName("Karmeliet")
                .beerStyle("Triple")
                .quantityOnHand(250)
                .upc("abc123")
                .price(BigDecimal.valueOf(10.99))
                .build();

        beerClient.createBeer(newDTO)
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testUpdateBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        final String NAME = "Leffe";

        beerClient.listBeersDto()
                .next()
                .doOnNext(beerDTO -> beerDTO.setBeerName(NAME))
                .flatMap(beerDTO -> beerClient.updateBeer(beerDTO))
                .subscribe(byIdDTO -> {
                    System.out.println(byIdDTO.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testPatchBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO patchDTO = BeerDTO.builder()
                .beerName("Leffe patch")
                .beerStyle("Blond patch")
                .build();

        beerClient.listBeersDto()
                .next()
                .doOnNext(beerDTO -> {
                    beerDTO.setBeerName(patchDTO.getBeerName());
                    beerDTO.setBeerStyle(patchDTO.getBeerStyle());
                })
                .flatMap(beerDTO -> beerClient.patchBeer(beerDTO))
                .subscribe(byIdDTO -> {
                    System.out.println(byIdDTO.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testDeleteBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeersDto()
                .next()
                .flatMap(beerDTO -> beerClient.deleteBeer(beerDTO))
                .doOnSuccess(mt -> atomicBoolean.set(true))
                .subscribe();

        await().untilTrue(atomicBoolean);

    }
}