package derry.club.pokemon.config;

import derry.club.pokemon.model.gamedata.poke.PokeTypeEntity;
import derry.club.pokemon.model.gamedata.poke.PokemonImpl;
import derry.club.pokemon.model.gamedata.poke.PokemonType;
import derry.club.pokemon.repository.PokeTypeEntityRepository;
import derry.club.pokemon.repository.PokemonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Configuration
@Slf4j
public class PokeConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner savePokemonEnumTypes(PokeTypeEntityRepository repository) {
        return args -> {
            for (PokemonType type : PokemonType.values()) {
                PokeTypeEntity entity = new PokeTypeEntity();
                entity.setPokeType(type);
                repository.save(entity);
            }
        };
    }

    /**
     * Save all pokemons in Generation I by requesting <a href="https://pokeapi.co/">pokemon API</a>
     *
     * @param pokemonRepository save pokemon
     */
    @Bean
    public CommandLineRunner run(PokemonRepository pokemonRepository, RestTemplate restTemplate) {
        return args -> {

            Instant start = Instant.now();

            ExecutorService service = ForkJoinPool.commonPool();

            String baseUrl = "https://pokeapi.co/api/v2/pokemon/id";

            List<URI> uris = IntStream.rangeClosed(1, 151)
                    .mapToObj(index -> {
                        String url = baseUrl.replace("id", String.valueOf(index));
                        return URI.create(url);
                    }).toList();

            List<Callable<PokemonImpl>> callablePokemons = uris.stream()
                    .map(uri -> (Callable<PokemonImpl>) () -> restTemplate.getForObject(uri, PokemonImpl.class))
                    .toList();

            var futureList = service.invokeAll(callablePokemons);

            List<PokemonImpl> pokes = new ArrayList<>();
            for (Future<PokemonImpl> pokemonFuture : futureList) {
                PokemonImpl pokemon = pokemonFuture.get();
                pokes.add(pokemon);
            }

            HashMap

            for (PokemonImpl each : pokes) {
                pokemonRepository.save(each);
            }

            Instant end = Instant.now();

            var seconds = Duration.between(start, end).toSeconds();

            log.info("{} seconds taken to request and save pokemon of Generation I", seconds);
        };
    }


}
