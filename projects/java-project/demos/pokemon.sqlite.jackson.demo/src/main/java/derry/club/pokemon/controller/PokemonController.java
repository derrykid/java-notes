package derry.club.pokemon.controller;

import derry.club.pokemon.model.gamedata.poke.CaughtPokemon;
import derry.club.pokemon.model.gamedata.poke.Pokemon;
import derry.club.pokemon.model.gamedata.poke.form.CaughtPokemonForm;
import derry.club.pokemon.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PokemonController {

    private final PokemonService service;

    @Autowired
    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping(path = "/pokemon/{id}")
    public Pokemon getPokemonById(@PathVariable("id") int id) {
        return service.getPokemonById(id);
    }

    @PostMapping(path = "/caught/{trainerId}")
    public void saveCaughtPokemon(@RequestBody CaughtPokemonForm caughtPokemonForm,
                                  @PathVariable int trainerId) {
        log.info("Trainer id: {} has caught pokemon: {}", trainerId, caughtPokemonForm);
        service.saveCaughtPokemon(caughtPokemonForm, trainerId);
    }

    @GetMapping(path = "/get/{id}")
    public CaughtPokemon getCaughtPokemon(@PathVariable("id") int id) {
        return service.getCaughtPokemon(id);
    }
}
