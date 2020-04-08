package Observers;

import Classes.Aircraft;
import Classes.Coordinates;
import Classes.WeatherTower;
import Interfaces.Flyable;
import Utils.*;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
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
                    this.coordinates.height += 4;
                    if (this.coordinates.height > 100){
                        this.coordinates.height = 100;
                    }
                    this.coordinates.longitude += 2;
                    Utils.conditionChangesToLog((Flyable) this, "Baloon", "Clear Skies Ahead. Sip that Champagne.\n");
                    break;
                case "RAIN":
                    this.coordinates.height -= 5;
                    Utils.conditionChangesToLog((Flyable) this, "Baloon", "Look at all this rain! I'm sinking..\n");
                    if (this.coordinates.height < 1){
                        this.coordinates.height = 0;
                        Utils.conditionChangesToLog(this, "Baloon", "Landing.\n");
                        Utils.flyableFromLog(this, "Baloon");
                        Globals.aircraftLanded = this;
                        break;
                    }
                    break;
                case "FOG":
                    this.coordinates.height -= 3;
                    Utils.conditionChangesToLog((Flyable) this, "Baloon", "I think we have taken off... can't see through all this fog!\n");
                    if (this.coordinates.height < 1){
                        this.coordinates.height = 0;
                        Utils.conditionChangesToLog(this, "Baloon", "Landing.\n");
                        Utils.flyableFromLog(this, "Baloon");
                        Globals.aircraftLanded = this;
                        break;
                    }
                    break;
                case "SNOW":
                    this.coordinates.height -= 15;
                    Utils.conditionChangesToLog((Flyable) this, "Baloon", "oh dear... Winter is coming.\n");
                    if (this.coordinates.height < 1){
                        this.coordinates.height = 0;
                        Utils.conditionChangesToLog(this, "Baloon", "Landing.\n");
                        Utils.flyableFromLog(this, "Baloon");
                        Globals.aircraftLanded = this;
                        break;
                    }
                    break;
            }
        } catch(Exception e){
            System.out.printf("Baloon.updateConditions failed --->%s\n", e.toString());
        }
    }
}