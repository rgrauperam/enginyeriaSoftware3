package mvc;

// import java.util.Observer; // This line was already commented out or removed in previous steps
// No need for java.util.Observer anymore

public interface TemperatureModelInterface {
    void on();
    void off();
    int getCurrentTemperature();
    int getTargetTemperature();
    void setTargetTemperature(int temp);

    void registerObserver(ModelObserver o); // Changed from java.util.Observer
    void removeObserver(ModelObserver o);   // Changed from java.util.Observer
}