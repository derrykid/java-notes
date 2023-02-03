package derry.club.pokemon.repository;

import derry.club.pokemon.model.gamedata.poke.PokeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokeTypeEntityRepository extends JpaRepository<PokeTypeEntity, Integer> {
}
