import Troll.*;

public class DecoratorDemo {

    public static void main(String[] args) {
        System.out.println("===A simple troll born====");
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.gainAttackPower();
        troll.flee();

        System.out.println("=======");
        System.out.println("After a while, the troll fought a lot monsters");

        System.out.println("===Evolve====");
        WarriorTroll trundle = new WarriorTroll(troll);
        trundle.attack();
        trundle.gainAttackPower();
        System.out.println("=======");
    }
}
