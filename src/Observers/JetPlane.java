package Observers;

import Classes.Aircraft;
import Classes.Coordinates;
import Classes.WeatherTower;
import Interfaces.Flyable;
import Utils.*;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
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
                    this.coordinates.latitude += 10;
                    if ((this.coordinates.height += 2) > 100){
                        this.coordinates.height = 100;
                    }
                    Utils.conditionChangesToLog((Flyable) this, "JetPlane", "Clear Skies Ahead. Time for fast.\n");
                    break;
                case "RAIN":
                    this.coordinates.latitude += 5;
                    Utils.conditionChangesToLog((Flyable) this, "JetPlane", "Look at all this rain! To the forward slightly less fast!\n");
                    break;
                case "FOG":
                    this.coordinates.latitude += 1;
                    Utils.conditionChangesToLog((Flyable) this, "JetPlane", "Fog is bad timez... no fast.!\n");
                    break;
                case "SNOW":
                    this.coordinates.height -= 7;
                    Utils.conditionChangesToLog((Flyable) this, "JetPlane", "oh dear... Snow = down and slow bad times.\n");
                    if (this.coordinates.height < 1){
                        this.coordinates.height = 0;
                        Utils.conditionChangesToLog(this, "JetPlane", "Landing.\n");
                        Utils.flyableFromLog(this, "JetPlane");
                        Globals.aircraftLanded = this;
                        break;
                    }
                    break;
            }
        } catch(Exception e){
            System.out.printf("JetPlane.updateConditions failed --->%s\n", e.toString());
        }
    }
}