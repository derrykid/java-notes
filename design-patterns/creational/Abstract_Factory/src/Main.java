import Abstract.FactoryMaker.*;

public class Main {
    public static void main(String[] args) {
        var factoryMaker = new FactoryMaker();
        var elfKingdomFactory = factoryMaker.makeFactory(KingdomType.ELF);

        Army army = elfKingdomFactory.createArmy();
        Castle elfOne = elfKingdomFactory.createCastle();
        King elfKing = elfKingdomFactory.createKing();

        System.out.println(army.getDescription());
        System.out.println(elfOne.getDescription());
        System.out.println(elfKing.getDescription());
    }
}

