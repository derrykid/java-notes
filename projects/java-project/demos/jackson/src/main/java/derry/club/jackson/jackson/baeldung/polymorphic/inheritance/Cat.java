package derry.club.jackson.jackson.baeldung.polymorphic.inheritance;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("cat")
public class Cat extends Animal {

    private int lives;

    public Cat(String name) {
        super(name);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
