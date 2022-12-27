package SlayStrategy;

public class LambdaStrategy {
    public enum Strategy implements DragonSlayStrategy {
        MeleeStrategy(() -> System.out.println("Melee strategy")),
        ProjectileStrategy(() -> System.out.println("Projectile strategy")),
        SpellStrategy(() -> System.out.println("Spell strategy"));

        private final DragonSlayStrategy strategy;

        Strategy(DragonSlayStrategy strategy) {
            this.strategy = strategy;
        }

        @Override
        public void execute() {
            strategy.execute();
        }
    }
}
