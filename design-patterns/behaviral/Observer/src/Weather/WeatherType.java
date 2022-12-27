package Weather;

public enum WeatherType {
    SUNNY("Bright"),
    RAINY("Starts to rain"),
    WINDY("It's windy"),
    COLD("It's cold");

    private final String weatherDescription;

    WeatherType(String desc){
        this.weatherDescription = desc;
    }
    public String getDescription(){
        return weatherDescription;
    }
}
