package mvc;

import java.util.Observer; // Required for the Observer pattern

public interface TemperatureModelInterface {
    void on();
    void off();
    int getCurrentTemperature();
    int getTargetTemperature();
    void setTargetTemperature(int temp);

    void registerObserver(Observer o);
    void removeObserver(Observer o);
}