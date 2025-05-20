package run;

import mvc.StandardTemperatureController;
import mvc.StandardTemperatureModel;
import mvc.TemperatureControllerInterface;
import mvc.TemperatureModelInterface;

public class StandardTemperatureTest {

    public static void main (String[] args) {
        TemperatureModelInterface model = new StandardTemperatureModel();
        TemperatureControllerInterface controller = new StandardTemperatureController(model, true);
    }


}
