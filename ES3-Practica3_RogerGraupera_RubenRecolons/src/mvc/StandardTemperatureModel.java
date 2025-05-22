package mvc;

import java.util.Random;

public class StandardTemperatureModel extends TemperatureObserver implements TemperatureModelInterface, Runnable {
    /*TODO: Ensure that  StandardTemperatureModel implements the corresponding interface (other than Runnable)*/

    private int targetTemperature;
    private int currentTemperature;
    private Thread thread;
    private volatile boolean stopThread;


    public StandardTemperatureModel(){
        Random rand = new Random();
        currentTemperature = rand.nextInt(0,31);
        targetTemperature = currentTemperature;
        stopThread = false;
    }

    @Override
    public void on() {
        stopThread = false;
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
        //TODO: may require additional code super.setChanged();
        super.notifyObservers(this);
    }

    @Override
    public void off() {
        stopThread = true;
        if (thread != null) {
            thread.interrupt();
        }
        //TODO: May require additional code
        super.setChanged();
        super.notifyObservers(this);
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
                if (stopThread) break;
                boolean modelStateChanged = false;
                if (currentTemperature < targetTemperature) {
                    currentTemperature++;
                    modelStateChanged = true;
                } else if (currentTemperature > targetTemperature) {
                    currentTemperature--;
                    modelStateChanged = true;
                }

                if (modelStateChanged) {
                    super.setChanged();
                    super.notifyObservers(this);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
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
        super.setChanged();
        super.notifyObservers(this);
    }

    @Override
    public void registerObserver(ModelObserver o) {
        super.addObserver(o);
    }

    @Override
    public void removeObserver(ModelObserver o) {
        super.deleteObserver(o);
    }
}