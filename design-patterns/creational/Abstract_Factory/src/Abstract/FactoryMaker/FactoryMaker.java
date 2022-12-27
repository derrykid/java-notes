package Abstract.FactoryMaker;

import KingdomFactory.*;

public class FactoryMaker {

    public static KingdomFactory makeFactory(KingdomType type){

       switch (type) {
           case ELF:
               return new ElfKingdomFactory();
               // some more kingdom type if require

           default:
               throw new IllegalArgumentException("Kingdom type not support.");
       }
    }

}
