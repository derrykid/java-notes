package derry.club.pokemon.service;

import derry.club.pokemon.exception.PokemonNotFoundException;
import derry.club.pokemon.exception.TrainerNotFoundException;
import derry.club.pokemon.model.gamedata.poke.CaughtPokemon;
import derry.club.pokemon.model.gamedata.poke.PokemonImpl;
import derry.club.pokemon.model.gamedata.poke.form.CaughtPokemonForm;
import derry.club.pokemon.model.gamedata.trainer.Trainer;
import derry.club.pokemon.repository.CaughtPokemonRepository;
import derry.club.pokemon.repository.PokemonRepository;
import derry.club.pokemon.repository.TrainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final CaughtPokemonRepository caughtPokemonRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public PokemonService(PokemonRepository repository,
                          CaughtPokemonRepository caughtPokemonRepository,
                          TrainerRepository trainerRepository) {
        this.pokemonRepository = repository;
        this.caughtPokemonRepository = caughtPokemonRepository;
        this.trainerRepository = trainerRepository;
    }


    public PokemonImpl getPokemonById(int id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(id));
    }

    @Transactional
    public void saveCaughtPokemon(CaughtPokemonForm caughtPokemonForm, int trainerId) {

        CaughtPokemon caughtPokemon = new CaughtPokemon();
        caughtPokemon.setPokedexId(caughtPokemonForm.getPokedexId());
        caughtPokemon.setLevel(caughtPokemonForm.getLevel());
        caughtPokemon.setItem(caughtPokemonForm.getItem());
        caughtPokemon.setName(caughtPokemonForm.getName());

        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new TrainerNotFoundException(trainerId));

        caughtPokemon.setTrainer(trainer);

        caughtPokemonRepository.save(caughtPokemon);
    }

    public CaughtPokemon getCaughtPokemon(int id) {
        return caughtPokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(id));
    }
}
