package Elf;

import Abstract.FactoryMaker.Castle;

public class ElfCastle implements Castle {

    static final String DESCRIPTION = "This is elf castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
