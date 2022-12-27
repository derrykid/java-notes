package Elf;

import Abstract.FactoryMaker.King;

public class ElfKing implements King {

    static final String DESCRIPTION = "This is elf king";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
