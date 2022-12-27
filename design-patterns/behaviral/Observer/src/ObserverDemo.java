import Observer.Hobbits;
import Observer.Orcs;
import Weather.Weather;

public class ObserverDemo {
    public static void main(String[] args) {
        var weather = new Weather();
        weather.addObserver(new Orcs());
        weather.addObserver(new Hobbits());
        weather.timePasses();
        weather.timePasses();
        weather.timePasses();
        weather.timePasses();
    }
}
