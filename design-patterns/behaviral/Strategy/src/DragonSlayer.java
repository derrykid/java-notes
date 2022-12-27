import SlayStrategy.DragonSlayStrategy;

public class DragonSlayer {

    private DragonSlayStrategy dragonSlayStrategy;

    public DragonSlayer(DragonSlayStrategy strategy) {
        this.dragonSlayStrategy = strategy;
    }

    public void changeStrategy(DragonSlayStrategy strategy) {
        this.dragonSlayStrategy = strategy;
    }

    public void goBattle() {
        dragonSlayStrategy.execute();
    }
}
