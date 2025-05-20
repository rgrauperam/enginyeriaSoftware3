package mvc;

import java.util.Random;
// No longer need java.util.Observable or java.util.Observer

public class StandardTemperatureModel extends TemperatureObserver implements TemperatureModelInterface, Runnable {

    private int targetTemperature;
    private int currentTemperature;
    private Thread thread;
    private volatile boolean stopThread; // Marked volatile as it's accessed by multiple threads


    public StandardTemperatureModel(){
        Random rand = new Random();
        currentTemperature = rand.nextInt(0,31);
        targetTemperature = currentTemperature;
        stopThread = false;
    }

    @Override
    public void on() {
        stopThread = false; // Reset flag before starting
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
        // Notify observers that the state might have changed (e.g., model is now "on")
        super.setChanged();
        super.notifyObservers(this);
    }

    @Override
    public void off() {
        stopThread = true;
        if (thread != null) {
            thread.interrupt(); // Interrupt the thread to stop it from sleeping
        }
        // Notify observers that the state might have changed (e.g., model is now "off")
        super.setChanged();
        super.notifyObservers(this);
    }

    @Override
    public void run() {
        while(!stopThread) {
            try {
                Thread.sleep(3000); // Sleep for 3 seconds
                if (stopThread) break; // Check flag again after sleep

                boolean modelStateChanged = false;
                if (currentTemperature < targetTemperature) {
                    currentTemperature++;
                    modelStateChanged = true;
                } else if (currentTemperature > targetTemperature) {
                    currentTemperature--;
                    modelStateChanged = true;
                }

                if (modelStateChanged) {
                    super.setChanged();    // Mark this object as having been changed
                    super.notifyObservers(this); // Notify all of its observers, passing this model instance
                }
            } catch (InterruptedException e) {
                // Thread was interrupted, likely by off() method
                // Allow the loop to terminate by checking stopThread
                Thread.currentThread().interrupt(); // Preserve interrupt status
            } catch (Exception e) {
                // Handle other potential exceptions
                e.printStackTrace();
            }
        }
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
        super.setChanged();
        super.notifyObservers(this);
    }

    @Override
    public void registerObserver(ModelObserver o) {
        super.addObserver(o); // Use method from TemperatureObserver superclass
    }

    @Override
    public void removeObserver(ModelObserver o) {
        super.deleteObserver(o); // Use method from TemperatureObserver superclass
    }
}