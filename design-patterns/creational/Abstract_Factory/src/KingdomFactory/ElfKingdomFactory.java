package KingdomFactory;

import Abstract.FactoryMaker.Army;
import Abstract.FactoryMaker.Castle;
import Abstract.FactoryMaker.King;
import Elf.ElfArmy;
import Elf.ElfCastle;
import Elf.ElfKing;

public class ElfKingdomFactory implements KingdomFactory {
    @Override
    public Army createArmy() {
        return new ElfArmy();
    }

    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public King createKing() {
        return new ElfKing();
    }
}
