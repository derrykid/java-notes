package derry.club.jackson.resttemplate.demo;

import derry.club.jackson.jackson.baeldung.read.Constructor_JsonCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class RequestSender {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {

            var pikachu = restTemplate.getForObject(
                    "https://pokeapi.co/api/v2/pokemon/pikachu",
                    Constructor_JsonCreator.class);
            log.info(pikachu.toString());
        };
    }

}
