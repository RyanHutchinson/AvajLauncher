package Utils;

import Interfaces.Flyable;
import java.lang.reflect.*;

public class Utils {

    // Adds registration of flyable to log during registration.
    public static void flyableToLog(Flyable flyable, String type) throws NoSuchFieldException {
        try {
            String log = "Tower says: " + type + "#" + reflect(flyable, "name") + "(" + reflect(flyable,"id") + ") registered to weather tower.\n";
            Globals.log.add(log);
        } catch(Exception e){
            System.out.printf("Utils.flyableToLog failed --->%s\n", e.toString());
        }
    }

    // Adds un-registration of flyable to log
    public static void flyableFromLog(Flyable flyable, String type) throws NoSuchFieldException {
        try{
            String log = "Tower says: " + type + "#" + reflect(flyable, "name") + "(" + reflect(flyable,"id") + ") unregistered from the weather tower.\n";
            Globals.log.add(log);
        } catch(Exception e){
            System.out.printf("Utils.flyableFromLog failed --->%s\n", e.toString());
        }
    }

    // Adds flyable response to log on conditions changed
    public static void conditionChangesToLog(Flyable flyable, String type, String message) throws NoSuchFieldException {
        try{
            String log = type + "#" + reflect(flyable, "name") + "(" + reflect(flyable,"id") + "): " + message;
            Globals.log.add(log);
        } catch(Exception e){
            System.out.printf("Utils.conditionChangesToLog failed --->%s\n", e.toString());
        }
    }

    // Helper to help me touch the untouchable(Protected properties)
    private static String reflect(Flyable flyable, String property){
        try {
            Field id = flyable.getClass().getSuperclass().getDeclaredField(property);
            id.setAccessible(true);
            return id.get(flyable).toString();
        } catch (Exception e){
            System.out.printf("Utils.reflect failed --->%s\n", e.toString());
            return null;
        }
    }

}
