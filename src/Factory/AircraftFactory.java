package Factory;

import java.io.*;
import Classes.Coordinates;
import Interfaces.Flyable;
import Observers.*;
import Utils.Globals;
import Utils.Utils;

public abstract class AircraftFactory {

    public Flyable newAircraft(String type, String name, Integer longitude, Integer latitude, Integer height){

        try{

            Flyable flyable;
            Coordinates coordinates = new Coordinates(longitude, latitude, height);

            switch(type){
                case "Baloon":
                    flyable = new Baloon(name,coordinates);
                    break;
                case "Helicopter":
                    flyable = new Helicopter(name,coordinates);
                    break;
                case "JetPlane":
                    flyable = new JetPlane(name,coordinates);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }

            Utils.flyableToLog(flyable, type);
            return flyable;

        } catch(Exception e){
            System.out.printf("AircraftFactory.newAircraft failed --->%s\n", e.toString());
            return null;
        }
    }

}
