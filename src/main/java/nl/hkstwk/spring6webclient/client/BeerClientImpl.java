package nl.hkstwk.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import nl.hkstwk.spring6webclient.model.BeerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {

    private final WebClient webClient;

    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    @Override
    public Flux<String> listBeers() {
        return webClient.get().uri("/api/v3/beer", String.class)
                .retrieve().bodyToFlux(String.class);
    }

    @Override
    public Flux<Map> listBeersMap() {
        return webClient.get().uri("/api/v3/beer", Map.class)
                .retrieve().bodyToFlux(Map.class);
    }

    @Override
    public Flux<JsonNode> listBeersJsonNode() {
        return webClient.get().uri("/api/v3/beer", JsonNode.class)
                .retrieve().bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<BeerDTO> listBeersDto() {
        return webClient.get().uri("/api/v3/beer", BeerDTO.class)
                .retrieve().bodyToFlux(BeerDTO.class);
    }
}
