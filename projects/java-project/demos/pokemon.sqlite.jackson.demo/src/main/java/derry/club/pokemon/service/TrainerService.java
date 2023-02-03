package derry.club.pokemon.service;

import derry.club.pokemon.model.gamedata.poke.CaughtPokemon;
import derry.club.pokemon.model.gamedata.trainer.Trainer;
import derry.club.pokemon.model.gamedata.trainer.form.TrainerForm;
import derry.club.pokemon.repository.CaughtPokemonRepository;
import derry.club.pokemon.repository.PokemonRepository;
import derry.club.pokemon.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final CaughtPokemonRepository caughtPokemonRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository,
                          CaughtPokemonRepository caughtPokemonRepository) {
        this.trainerRepository = trainerRepository;
        this.caughtPokemonRepository = caughtPokemonRepository;
    }

    @Transactional
    public void createTrainer(TrainerForm trainerForm) {

        Trainer trainer = new Trainer();

        ZonedDateTime now = ZonedDateTime.now();

        trainer.setTimeCreated(now);
        trainer.setLastLogin(now);
        trainer.setName(trainerForm.getName());
        trainer.setMoney(trainerForm.getMoney());
        trainer.setGender(trainerForm.getGender());


        var out = trainerRepository.save(trainer);
        System.out.println(out);
    }

    public List<CaughtPokemon> getAllPokemon(int trainerId) {
        return caughtPokemonRepository.findByTrainerId(trainerId);
    }
}
