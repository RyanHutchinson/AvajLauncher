package Classes;

import Interfaces.Flyable;
import Utils.Globals;
import java.util.*;

public class Tower {
    public ArrayList<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable){
        try{
            this.observers.add(flyable);
            flyable.registerTower((WeatherTower)this);
        } catch (Exception e){
            System.out.printf("Tower.register failed due to --->%s\n", e.toString());
        }
    }

    public void unregister(Flyable flyable){
        try{
            this.observers.remove(flyable);
        } catch (Exception e){
            System.out.printf("Tower.unregister failed due to --->%s\n", e.toString());
        }
    }

    public void conditionsChanged(){
    try{
        if (observers.contains(Globals.aircraftLanded)){ // If an aircraft to be removed from tower before updates.
            unregister(Globals.aircraftLanded);
        }

        for (Flyable flyable : observers) { //simulation
            try{
                flyable.updateConditions();
            } catch (Exception e){
                System.out.printf("Tower.conditionsChanged failed in catch 1 due to --->%s\n", e.toString());
            }
        }
    } catch(Exception e){
        System.out.printf("Tower.conditionsChanged failed in catch 2 due to --->%s\n", e.toString());
    }

    }
}
