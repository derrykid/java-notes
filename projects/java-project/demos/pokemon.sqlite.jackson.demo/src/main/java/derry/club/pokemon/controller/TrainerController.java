package derry.club.pokemon.controller;

import derry.club.pokemon.model.gamedata.poke.CaughtPokemon;
import derry.club.pokemon.model.gamedata.trainer.Trainer;
import derry.club.pokemon.model.gamedata.trainer.form.TrainerForm;
import derry.club.pokemon.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping(path = "/trainer")
    public void createTrainer(@RequestBody TrainerForm trainerForm) {
        trainerService.createTrainer(trainerForm);
    }

    @GetMapping(path = "/trainer/{id}/all")
    public List<CaughtPokemon> getAllCaughtPokemon(@PathVariable("id") int trainerId) {
        return trainerService.getAllPokemon(trainerId);
    }


}
