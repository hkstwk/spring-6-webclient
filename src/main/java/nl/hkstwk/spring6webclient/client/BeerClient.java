package nl.hkstwk.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import nl.hkstwk.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {
    Flux<String> listBeers();
    Flux<Map> listBeersMap();
    Flux<JsonNode> listBeersJsonNode();
    Flux<BeerDTO> listBeersDto();
    Mono<BeerDTO> getBeerById(String beerId);
    Flux<BeerDTO> getBeerByStyle(String beerStyle);
    Mono<BeerDTO> createBeer(BeerDTO beerDTO);
    Mono<BeerDTO> updateBeer(BeerDTO beerDTO);
    Mono<BeerDTO> patchBeer(BeerDTO beerDTO);
}
