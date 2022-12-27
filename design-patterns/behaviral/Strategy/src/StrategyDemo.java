import SlayStrategy.MeleeStrategy;
import SlayStrategy.ProjectileStrategy;
import SlayStrategy.SpellStrategy;

import java.math.BigInteger;

public class StrategyDemo {
    public static void main(String[] args) {
        /*Melee Strategy*/
        var slayer = new DragonSlayer(new MeleeStrategy());
        slayer.goBattle();

        /*Spell Strategy*/
        slayer.changeStrategy(new SpellStrategy());
        slayer.goBattle();

        /*Projectile Strategy*/
        slayer.changeStrategy(new ProjectileStrategy());
        slayer.goBattle();
    }
}
