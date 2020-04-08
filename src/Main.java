import java.io.*;
import Classes.*;
import Factory.AircraftFactory;
import Interfaces.Flyable;
import Utils.*;

public class Main {

    public static void main(String[] args) {

        // No Argument Given. Send error to terminal
        if (args.length == 0){
            System.out.print("You have given me nothing....\nPlease give me an argument. -_-\n");
            return;
        }

        WeatherTower weatherTower = new WeatherTower(); //holds flyables
        final AircraftFactory factory = new AircraftFactory() {}; //makes flyables

        try{ // Parse argument into globals(log and iterations) & weatherTower.

            File file = new File(args[0]);
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            boolean firstLineExists = true;
            String line;

            while((line = buffer.readLine()) != null){

                if(firstLineExists){ // Set Iterations

                    try {
                        Globals.iterations = Integer.parseInt(line);
                        System.out.printf("\nSimulation will run %d times.\n\n", Globals.iterations);
                    } catch(Exception e) {
                        Globals.iterations = 1;
                        System.out.printf("Iterations set to one due to parsing error--->%s\n", e.toString());
                    }

                    firstLineExists = false;

                } else { // Make and add Flyables

                    Flyable flyable;

                    String[] aircraft = new String[0];
                    try {
                        aircraft = line.split(" ");
                        flyable = factory.newAircraft(aircraft[0], aircraft[1], Integer.parseInt(aircraft[2]),
                                Integer.parseInt(aircraft[3]), Integer.parseInt(aircraft[4]));
                        weatherTower.register(flyable);
                    } catch (Exception e) {
                        System.out.printf("Couldn't add Aircraft %s due to error in main.parsing --->%s\n", aircraft[1], e.toString());
                    }
                }
            }
            buffer.close();

        } catch(Exception e) {
            System.out.printf("Something went terribly wrong in Main.parsing --->%s", e.toString());
            return;
        }

        // Iterate Simulations
        for (int i = 0; i < Globals.iterations; i++){
            try{
                weatherTower.conditionsChanged();
            } catch(Exception e){
                System.out.printf("Something went terribly wrong in main.simulation --->%s\n", e.toString());
            }
        }

        // Write to log file
        try{

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Simulation.txt"));

            for (String log : Globals.log){
                bufferedWriter.write(log);
                System.out.print(log);
            }

            bufferedWriter.close();

        } catch(Exception e){
            System.out.printf("Something went wrong in Main.Logwriter ---> %s\n", e.toString());
        }

        System.out.print("Job done.\n");
    }
}
