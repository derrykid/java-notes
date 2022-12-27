package components;

public class Engine {
    private final double volumn;
    private double mileage;
    private boolean started;

    public Engine(double volumn, double mileage){
        this.volumn = volumn;
        this.mileage = mileage;
    }

    public void on(){
        started = true;
    }

    public void off(){
        started = false;
    }

    public boolean isStarted(){
        return started;
    }

    public void go(double mileage){
        if (started){
            this.mileage += mileage;
        } else {
            System.err.println("Cannot go(), you must start engine first");
        }
    }

    public double getVolume(){
        return volumn;
    }

    public double getMileage(){
        return mileage;
    }
}
