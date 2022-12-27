package KingdomFactory;

import Abstract.FactoryMaker.Army;
import Abstract.FactoryMaker.Castle;
import Abstract.FactoryMaker.King;

public interface KingdomFactory {
    Army createArmy();
    Castle createCastle();
    King createKing();
}
