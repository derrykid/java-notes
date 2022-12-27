public class PeacefulState implements State{

    private final Mammoth mammoth;

    public PeacefulState(Mammoth mammoth){
        this.mammoth = mammoth;
    }


    @Override
    public void onEnterState() {
        System.out.println(mammoth + " calms down");
    }

    @Override
    public void observe() {
        System.out.println(this.mammoth + " is peaceful");
    }
}
