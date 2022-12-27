package Troll;

public class SimpleTroll implements Troll{

    @Override
    public void attack() {
        System.out.println("A troll is attacking");
    }

    @Override
    public void gainAttackPower() {
        System.out.println("A troll has a buff");
    }

    @Override
    public void flee() {
        System.out.println("A troll flees");
    }
}
