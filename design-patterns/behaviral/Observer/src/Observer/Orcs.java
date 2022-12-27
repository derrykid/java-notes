package Observer;
import Weather.*;

public class Orcs implements WeatherObserver{
    @Override
    public void update(WeatherType currentWeather) {
        System.out.println("The Orcs see: " + currentWeather.getDescription());
    }
}
