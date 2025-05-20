package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReadOnlyTemperatureModel implements Runnable{
    /*TODO: Ensure that ReadOnlyTemperatureModel implements the corresponding interface (other than Runnable)*/

    private int currentTemperature;
    private List<Observer> observerList;
    private Thread thread;
    private boolean stopThread;

    public ReadOnlyTemperatureModel(){
        Random rand = new Random();
        observerList = new ArrayList<>();
        currentTemperature = rand.nextInt(-30,40);
    }


    @Override
    public void on() {
        thread = new Thread(this);
        thread.start();
        stopThread = false;
        //TODO: may require additional code
    }

    @Override
    public void off() {
        stopThread = true;
        thread.interrupt();
        //TODO: may require additional code
    }

    /*TODO: Complete with appropriate methods and ensure that they work as expected. You may also need to add code to
     *  on and off methods above*/

    @Override
    public void run() {
        while (!stopThread) {
            try {
                Thread.sleep(2000);
                Random rand = new Random();
                currentTemperature += rand.nextInt(-1, 2);
                System.out.println("Current temperature " + currentTemperature); //This is left for easier debugging
                //TODO: View should be updated
            } catch (Exception e) {

            }
        }
        stopThread = false;
    }

}
