package mvc;

import java.util.ArrayList;
import java.util.List;

public class TemperatureObserver { // This is the "Observable" base class
    private transient List<ModelObserver> actualObservers = new ArrayList<>();
    private boolean changed = false;

    public synchronized void addObserver(ModelObserver o) {
        if (o == null) {
            throw new NullPointerException("Observer cannot be null");
        }
        if (!actualObservers.contains(o)) {
            actualObservers.add(o);
        }
    }

    public synchronized void deleteObserver(ModelObserver o) {
        actualObservers.remove(o);
    }

    protected synchronized void setChanged() {
        this.changed = true;
    }

    protected synchronized void clearChanged() {
        this.changed = false;
    }

    public synchronized boolean hasChanged() {
        return this.changed;
    }

    public void notifyObservers(TemperatureModelInterface modelSource) {
        List<ModelObserver> observersToNotify;
        synchronized (this) {
            if (!hasChanged()) {
                return;
            }
            // Create a copy for safe iteration, in case an observer tries to unregister during notification
            observersToNotify = new ArrayList<>(this.actualObservers);
            clearChanged();
        }

        for (ModelObserver observer : observersToNotify) {
            observer.update(modelSource);
        }
    }
}