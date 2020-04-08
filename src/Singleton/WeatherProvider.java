package Singleton;

import Classes.Coordinates;
import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = null;
    private static String[] weather = {"RAIN","FOG","SUN","SNOW"};

    private WeatherProvider(){}

    public static WeatherProvider getProvider(){
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){
        Random random = new Random();
        return weather[random.nextInt(4)];
    }
}
