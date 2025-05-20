package mvc;

public class ReadOnlyTemperatureController implements TemperatureControllerInterface {
    /*TODO: Ensure that it implements the right interface*/

    TemperatureModelInterface model;
    TemperatureView view;

    public ReadOnlyTemperatureController(TemperatureModelInterface model) {
        this.model = model;

        /*TODO: Create view and assign class attributes*/
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

    /*TODO: Complete with the interface methods. Some tips below.*/

    /*TODO: Start method is invoked when the user clicks File->Start
     * It should invoke model.on
     * It should also prepare the UI, enabling and disabling the right elements
     *
     *  */
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

    /*TODO: Start method is invoked when the user clicks File->Stop
     * It should invoke model.off
     * It should also prepare the UI, enabling and disabling the right elements
     *
     *  */
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