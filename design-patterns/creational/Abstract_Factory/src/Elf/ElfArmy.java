package Elf;

import Abstract.FactoryMaker.Army;

public class ElfArmy implements Army {

    static final String DESCRIPTION = "This is elf army";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
