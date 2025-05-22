package mvc;

public interface TemperatureModelInterface {
    void on();
    void off();
    int getCurrentTemperature();
    int getTargetTemperature();
    void setTargetTemperature(int temp);

    void registerObserver(ModelObserver o); // Changed from java.util.Observer
    void removeObserver(ModelObserver o);   // Changed from java.util.Observer
}