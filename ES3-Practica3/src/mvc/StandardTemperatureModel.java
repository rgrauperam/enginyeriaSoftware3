package mvc;

import java.util.Random;
// Import Observable and Observer
import java.util.Observable;
import java.util.Observer;

public class StandardTemperatureModel extends Observable implements TemperatureModelInterface, Runnable {
    /*TODO: Ensure that  StandardTemperatureModel implements the corresponding interface (other than Runnable)*/

    private int targetTemperature;
    private int currentTemperature;
    private Thread thread;
    private volatile boolean stopThread; // Marked volatile as it's accessed by multiple threads


    public StandardTemperatureModel(){
        Random rand = new Random();
        currentTemperature = rand.nextInt(0,31);
        targetTemperature = currentTemperature;
        // observers list is removed, Observable superclass handles this
        stopThread = false;
    }

    @Override
    public void on() {
        stopThread = false; // Reset flag before starting
        thread = new Thread(this);
        thread.start();
        //TODO: may require additional code
        // Notify observers that the state might have changed (e.g., model is now "on")
        setChanged();
        notifyObservers();
    }

    @Override
    public void off() {
        stopThread = true;
        if (thread != null) {
            thread.interrupt(); // Interrupt the thread to stop it from sleeping
        }
        //TODO: may require additional code
        // Notify observers that the state might have changed (e.g., model is now "off")
        setChanged();
        notifyObservers();
    }

    /*TODO: Complete with appropriate methods and ensure that they work as expected. You may also need to add code to
     *  on and off methods above*/

    @Override
    public void run() {
        while(!stopThread) {
            try {
                Thread.sleep(3000); // Sleep for 3 seconds
                //TODO: Add code here to ensure that current temperature moves towards target temperature (in +1 or -1 increments).
                //TODO: View should be updated
                if (stopThread) break; // Check flag again after sleep

                boolean changed = false;
                if (currentTemperature < targetTemperature) {
                    currentTemperature++;
                    changed = true;
                } else if (currentTemperature > targetTemperature) {
                    currentTemperature--;
                    changed = true;
                }

                if (changed) {
                    setChanged();    // Mark this object as having been changed
                    notifyObservers(); // Notify all of its observers
                }
            } catch (InterruptedException e) {
                // Thread was interrupted, likely by off() method
                // Allow the loop to terminate by checking stopThread
            } catch (Exception e) {
                // Handle other potential exceptions
                e.printStackTrace();
            }
        }
        // stopThread is already true here, no need to reset to false unless on() immediately reuses it
    }

    @Override
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public int getTargetTemperature() {
        return targetTemperature;
    }

    @Override
    public void setTargetTemperature(int temp) {
        this.targetTemperature = temp;
        // Notify observers that the target temperature has changed
        setChanged();
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        this.addObserver(o); // Use method from Observable superclass
    }

    @Override
    public void removeObserver(Observer o) {
        this.deleteObserver(o); // Use method from Observable superclass
    }
}