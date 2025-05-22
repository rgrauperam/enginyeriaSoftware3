package mvc;

public class StandardTemperatureController implements TemperatureControllerInterface {
    /*TODO: Ensure that it implements the right interface*/

    TemperatureModelInterface model;
    TemperatureView view;
    boolean controlEnabled;

    public StandardTemperatureController(TemperatureModelInterface model, boolean controlEnabled){
        this.model = model;
        this.controlEnabled = controlEnabled;

        /*TODO: Create view and assign class attributes*/
        this.view = new TemperatureView(this, model);
        view.createView();
        view.createControls();

        /*TODO: Prepare UI */
        view.disableStopMenuItem();
        view.enableStartMenuItem();
        view.disableIncreaseButton();
        view.disableDecreaseButton();
        view.disableSetButton();
        view.setTargetTemperatureEditable(false);

        if (!controlEnabled) {
            view.setTargetTemperatureDisplay("N/A");
        } else {
            view.setTargetTemperatureDisplay(String.valueOf(model.getTargetTemperature()));
        }
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
        if (controlEnabled) {
            view.enableIncreaseButton();
            view.enableDecreaseButton();
            view.enableSetButton();
            view.setTargetTemperatureEditable(true);
        } else {
            view.disableIncreaseButton();
            view.disableDecreaseButton();
            view.disableSetButton();
            view.setTargetTemperatureEditable(false);
            view.setTargetTemperatureDisplay("N/A");
        }
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
        view.disableIncreaseButton();
        view.disableDecreaseButton();
        view.disableSetButton();
        view.setTargetTemperatureEditable(false);
        view.setTargetTemperatureDisplay("offline");
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