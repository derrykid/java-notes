package derry.club.pokemon.model.gamedata.trainer.form;

import derry.club.pokemon.model.gamedata.trainer.Trainer;
import lombok.Data;

@Data
public class TrainerForm {

    private Trainer.Gender gender;
    private String name;
    private int money;

}
