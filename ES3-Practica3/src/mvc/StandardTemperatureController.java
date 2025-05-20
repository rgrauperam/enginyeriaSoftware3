package mvc;

public class StandardTemperatureController implements TemperatureControllerInterface {

    TemperatureModelInterface model;
    TemperatureView view;
    boolean controlEnabled;

    public StandardTemperatureController(TemperatureModelInterface model, boolean controlEnabled){
        this.model = model;
        this.controlEnabled = controlEnabled;

        this.view = new TemperatureView(this, model);
        view.createView();
        view.createControls();

        // Initial UI state
        view.disableStopMenuItem();
        view.enableStartMenuItem();
        view.disableIncreaseButton();
        view.disableDecreaseButton();
        view.disableSetButton();
        view.setTargetTemperatureEditable(false);

        if (!controlEnabled) {
            view.setTargetTemperatureDisplay("N/A");
        } else {
            // When control is enabled, target initially matches current or is set by model.
            // The view's update method will handle displaying it if controls are active.
            // Or, explicitly set it based on the model's initial target.
            view.setTargetTemperatureDisplay(String.valueOf(model.getTargetTemperature()));
        }
        view.setCurrentTemperatureDisplay(String.valueOf(model.getCurrentTemperature()));
    }

    @Override
    public void start() {
        model.on();
        view.enableStopMenuItem();
        view.disableStartMenuItem();
        if (controlEnabled) {
            view.enableIncreaseButton();
            view.enableDecreaseButton();
            view.enableSetButton();
            view.setTargetTemperatureEditable(true);
            // Target temperature display will be updated by the view's update() method
            // as it pulls from the model and tempTextField is editable.
        } else {
            view.disableIncreaseButton();
            view.disableDecreaseButton();
            view.disableSetButton();
            view.setTargetTemperatureEditable(false);
            view.setTargetTemperatureDisplay("N/A"); // Ensure N/A for monitor-only
        }
    }

    @Override
    public void stop() {
        model.off();
        view.disableStopMenuItem();
        view.enableStartMenuItem();
        view.disableIncreaseButton();
        view.disableDecreaseButton();
        view.disableSetButton();
        view.setTargetTemperatureEditable(false);
        view.setTargetTemperatureDisplay("offline"); // Set target display to "offline"
    }

    @Override
    public void increaseTargetTemperature() {
        if (controlEnabled) {
            model.setTargetTemperature(model.getTargetTemperature() + 1);
        }
    }

    @Override
    public void decreaseTargetTemperature() {
        if (controlEnabled) {
            model.setTargetTemperature(model.getTargetTemperature() - 1);
        }
    }

    @Override
    public void setTargetTemperature(int temp) {
        if (controlEnabled) {
            model.setTargetTemperature(temp);
        }
    }
}