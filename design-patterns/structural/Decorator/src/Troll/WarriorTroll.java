package Troll;

public class WarriorTroll implements Troll{

    private final Troll decorator;

    public WarriorTroll(Troll decor){
        this.decorator = decor;
    }

    @Override
    public void attack() {
        decorator.attack();
        System.out.println("And he punches you again!");
    }

    @Override
    public void gainAttackPower() {
        decorator.gainAttackPower();
        System.out.println("It's attack speed increase by the move");
    }

    @Override
    public void flee() {
        decorator.flee();
        System.out.println("Your confidence decrease," +
                " because Warrior shouldn't be afraid");
    }
}
