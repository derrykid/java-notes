package derry.club.jackson.jackson.baeldung.polymorphic.inheritance;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("dogValue")
public class Dog extends Animal {

    private int barkVolume;

    public Dog(String name) {
        super(name);
    }

    public int getBarkVolume() {
        return barkVolume;
    }

    public void setBarkVolume(int barkVolume) {
        this.barkVolume = barkVolume;
    }
}
