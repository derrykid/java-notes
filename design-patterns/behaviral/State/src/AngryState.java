public class AngryState implements State {
    private final Mammoth mammoth;

    public AngryState(Mammoth mammoth){
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        System.out.println(this.mammoth + " gets angry");

    }

    @Override
    public void observe() {
        System.out.println(this.mammoth + " is furious");
    }
}
