package Observers;

import Classes.*;
import Interfaces.*;
import Utils.*;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public void updateConditions() {
        try {
            String weather = weatherTower.getWeather(this.coordinates);
            switch (weather) {
                case "SUN":
                    this.coordinates.longitude += 10;
                    if ((this.coordinates.height += 2) > 100){
                        this.coordinates.height = 100;
                    }
                    Utils.conditionChangesToLog((Flyable) this, "Helicopter", "Clear Skies Ahead. Time for fast in the other direction.\n");
                    break;
                case "RAIN":
                    this.coordinates.longitude += 5;
                    Utils.conditionChangesToLog((Flyable) this, "Helicopter", "Look at all this rain! To the diagonal slightly less fast!\n");
                    break;
                case "FOG":
                    this.coordinates.longitude += 1;
                    Utils.conditionChangesToLog((Flyable) this, "Helicopter", "Fog is bad timez... no fast.!\n");
                    break;
                case "SNOW":
                    this.coordinates.height -= 12;
                    Utils.conditionChangesToLog((Flyable) this, "Helicopter", "oh dear... Snow = fast down.\n");
                    if (this.coordinates.height < 1){
                        this.coordinates.height = 0;
                        Utils.conditionChangesToLog(this, "Helicopter", "Landing.\n");
                        Utils.flyableFromLog(this, "Helicopter");
                        Globals.aircraftLanded = this;
                        break;
                    }
                    break;
            }
        } catch(Exception e){
            System.out.printf("Helicopter.updateConditions failed --->%s\n", e.toString());
        }
    }
}