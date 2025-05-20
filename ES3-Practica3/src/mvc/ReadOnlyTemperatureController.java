package mvc;

public class ReadOnlyTemperatureController implements TemperatureControllerInterface {

    TemperatureModelInterface model;
    TemperatureView view;

    public ReadOnlyTemperatureController(TemperatureModelInterface model) {
        this.model = model;

        this.view = new TemperatureView(this, model);
        view.createView();
        view.createControls();

        // Initial UI state for read-only mode
        view.disableStopMenuItem();
        view.enableStartMenuItem();
        view.disableIncreaseButton();
        view.disableDecreaseButton();
        view.disableSetButton();
        view.setTargetTemperatureEditable(false);
        view.setTargetTemperatureDisplay("N/A");
        view.setCurrentTemperatureDisplay(String.valueOf(model.getCurrentTemperature()));
    }

    @Override
    public void start() {
        model.on();
        view.enableStopMenuItem();
        view.disableStartMenuItem();
        // Ensure controls remain disabled and target display is N/A
        view.disableIncreaseButton();
        view.disableDecreaseButton();
        view.disableSetButton();
        view.setTargetTemperatureEditable(false);
        view.setTargetTemperatureDisplay("N/A");
    }

    @Override
    public void stop() {
        model.off();
        view.disableStopMenuItem();
        view.enableStartMenuItem();
        // Controls remain disabled
        view.disableIncreaseButton();
        view.disableDecreaseButton();
        view.disableSetButton();
        view.setTargetTemperatureEditable(false);
        view.setTargetTemperatureDisplay("offline");
    }

    @Override
    public void increaseTargetTemperature() {
        // No-op in read-only mode
    }

    @Override
    public void decreaseTargetTemperature() {
        // No-op in read-only mode
    }

    @Override
    public void setTargetTemperature(int temp) {
        // No-op in read-only mode
    }
}