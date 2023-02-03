package derry.club.jackson.jackson.baeldung;

import com.fasterxml.jackson.databind.ObjectMapper;
import derry.club.jackson.entity.Pokemon;

import java.io.IOException;
import java.net.URL;

public class PlayGround {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        String urlLocation = "https://pokeapi.co/api/v2/pokemon/";
        for (int id = 1; id <= 150; id++ ) {
            URL url = new URL(urlLocation + id);

            var pokemon = new ObjectMapper()
                    .readValue(url, Pokemon.class);

            System.out.println(pokemon);
        }

        long end = System.currentTimeMillis();

        long period = end - start;

        System.out.println(period / 1000);

    }
}
