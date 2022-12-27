package Observer;

import Weather.WeatherType;

public interface WeatherObserver {
    void update(WeatherType currentWeather);
}
