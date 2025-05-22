package mvc;

import java.util.Random;
// No longer need java.util.Observable or java.util.Observer

public class ReadOnlyTemperatureModel extends TemperatureObserver implements TemperatureModelInterface, Runnable {
    /*TODO: Ensure that ReadOnlyTemperatureModel implements the corresponding interface (other than Runnable)*/

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
        //TODO: may require additional code
        super.setChanged();
        super.notifyObservers(this);
    }

    @Override
    public void off() {
        stopThread = true;
        if (thread != null) {
            thread.interrupt();
        }
        //TODO: may require additional code
        super.setChanged();
        super.notifyObservers(this);
    }

    /*TODO: Complete with appropriate methods and ensure that they work as expected. You may also need to add code to
     *  on and off methods above*/

    @Override
    public void run() {
        while (!stopThread) {
            try {
                Thread.sleep(3000);
                if (stopThread) break;

                int change = random.nextInt(3) - 1; // -1, 0, or 1
                currentTemperature += change;

                System.out.println("Current temperature " + currentTemperature);

                if (currentTemperature < 0) currentTemperature = 0;
                if (currentTemperature > 40) currentTemperature = 40; // Example range

                //TODO: View should be updated
                super.setChanged();
                super.notifyObservers(this);
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
        return currentTemperature;
    }

    @Override
    public void setTargetTemperature(int temp) {
        // This model is read-only for target temperature; this method does nothing.
        // No notification needed as state doesn't change.
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