package mvc;

public interface TemperatureControllerInterface {
    void start();
    void stop();
    void increaseTargetTemperature();
    void decreaseTargetTemperature();
    void setTargetTemperature(int temp);
}