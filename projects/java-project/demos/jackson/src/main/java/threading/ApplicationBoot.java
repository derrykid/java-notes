package threading;

import com.fasterxml.jackson.databind.ObjectMapper;
import derry.club.jackson.entity.Pokemon;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ApplicationBoot {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        Instant start = Instant.now();

        // start of primary section

//        ForkJoinPool service = ForkJoinPool.commonPool();
        ExecutorService service = Executors.newCachedThreadPool();
//        ExecutorService service = Executors.newWorkStealingPool();

        List<Callable<Pokemon>> tasks = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> getPokemon(i))
                .toList();

        var futureList = service.invokeAll(tasks);

        for (Future<Pokemon> pokemonFuture : futureList) {
            System.out.println(pokemonFuture.get());
        }



        // end of primary section

        Instant end = Instant.now();

        long duration = Duration.between(start, end).toMillis();

        System.out.printf("Total %s milliseconds taken.", duration);

//        service.shutdown();
    }

    private static void notify(Pokemon pokemon) {
        System.out.println("Get pokemon done: " + pokemon);
    }

    private static Callable<Pokemon> getPokemon(int id) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " trying to get pokemon id: " + id);
            String baseUrl = "https://pokeapi.co/api/v2/pokemon/";
            URL url = new URL(baseUrl + id);
            Pokemon pokemon = new ObjectMapper().readValue(url, Pokemon.class);
            return pokemon;
        };
    }

}

