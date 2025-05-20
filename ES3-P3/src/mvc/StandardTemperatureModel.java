package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StandardTemperatureModel implements Runnable {
    /*TODO: Ensure that  StandardTemperatureModel implements the corresponding interface (other than Runnable)*/

    private List<Observer> observers;
    private int targetTemperature;
    private int currentTemperature;
    private Thread thread;
    private boolean stopThread;


    public StandardTemperatureModel(){
        Random rand = new Random();
        currentTemperature = rand.nextInt(0,31);
        targetTemperature = currentTemperature;
        observers = new ArrayList<>();
        stopThread = false;

    }

    @Override
    public void on() {
        thread = new Thread(this);
        thread.start();
        //TODO: may require additional code
    }

    @Override
    public void off() {
        stopThread = true;
        thread.interrupt();
        //TODO: May require additional code
    }

    /*TODO: Complete with appropriate methods and ensure that they work as expected. You may also need to add code to
    *  on and off methods above*/

    @Override
    public void run() {
        while(!stopThread) {
            try {
                Thread.sleep(3000);
                //TODO: Add code here to ensure that current temperature moves towards target temperature (in +1 or -1 increments).
                //TODO: View should be updated
            } catch (Exception e) {

            }
        }
        stopThread = false; //We reset stopThread
    }


}
