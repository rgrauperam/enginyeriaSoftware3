package mvc;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class ReadOnlyTemperatureModel extends Observable implements TemperatureModelInterface, Runnable {

    private int currentTemperature;
    private Thread thread;
    private volatile boolean stopThread;
    private Random random = new Random();

    public ReadOnlyTemperatureModel() {
        currentTemperature = random.nextInt(0, 31); // Initial random temperature
        stopThread = false;
    }

    @Override
    public void on() {
        stopThread = false;
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public void off() {
        stopThread = true;
        if (thread != null) {
            thread.interrupt();
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        while (!stopThread) {
            try {
                Thread.sleep(3000);
                if (stopThread) break;

                int change = random.nextInt(3) - 1; // -1, 0, or 1
                currentTemperature += change;

                if (currentTemperature < 0) currentTemperature = 0;
                if (currentTemperature > 40) currentTemperature = 40; // Example range

                setChanged();
                notifyObservers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                break;
            } catch (Exception e) {
                e.printStackTrace(); // Log other exceptions
            }
        }
    }

    @Override
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public int getTargetTemperature() {
        // In read-only mode, the actual value of target temperature from the model
        // is not directly displayed if controls are disabled. The controller sets "N/A".
        // Returning current temperature is a safe default.
        return currentTemperature;
    }

    @Override
    public void setTargetTemperature(int temp) {
        // This model is read-only for target temperature; this method does nothing.
    }

    @Override
    public void registerObserver(Observer o) {
        this.addObserver(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.deleteObserver(o);
    }
}