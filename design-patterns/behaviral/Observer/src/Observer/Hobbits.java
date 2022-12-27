package Observer;

import Weather.WeatherType;

public class Hobbits implements WeatherObserver{
    @Override
    public void update(WeatherType currentWeather) {
        System.out.println("Hobbits see: " + currentWeather.getDescription());
    }
}
