package Utils;

import Interfaces.Flyable;
import java.util.ArrayList;


// this is apparently naughty. I do not hold this line of thought. Fight me.
public class Globals {

    // Session storage of the log. Is written to file at end of runtime
    public static ArrayList<String> log = new ArrayList<String>();
    // Iterations of simulation pulled from file
    public static Integer iterations = 0;
    // Storage of landed aircraft to circumvent Concurrency issues with the arraylists.
    public static Flyable aircraftLanded;
}
