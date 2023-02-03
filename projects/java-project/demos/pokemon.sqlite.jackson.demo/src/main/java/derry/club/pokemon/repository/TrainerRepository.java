package derry.club.pokemon.repository;

import derry.club.pokemon.model.gamedata.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}
