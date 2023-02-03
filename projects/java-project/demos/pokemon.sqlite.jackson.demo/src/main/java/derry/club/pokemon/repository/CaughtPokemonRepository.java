package derry.club.pokemon.repository;

import derry.club.pokemon.model.gamedata.poke.CaughtPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaughtPokemonRepository extends JpaRepository<CaughtPokemon, Integer> {

    // select * from caughtPokemon where trainer_id = trainer_id
    @Query("SELECT c FROM CaughtPokemon c WHERE trainer_id = :id")
    List<CaughtPokemon> findByTrainerId(@Param("id") int trainerId);
}
