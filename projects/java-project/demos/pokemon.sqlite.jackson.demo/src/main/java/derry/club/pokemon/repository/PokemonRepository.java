package derry.club.pokemon.repository;

import derry.club.pokemon.model.gamedata.poke.PokemonImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonImpl, Integer> {
}
