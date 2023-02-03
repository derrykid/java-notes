package derry.club.pokemon.repository;

import derry.club.pokemon.model.gamedata.pocket.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
