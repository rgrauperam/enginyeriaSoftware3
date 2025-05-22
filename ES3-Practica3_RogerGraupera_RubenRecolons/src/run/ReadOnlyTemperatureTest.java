package run;

import mvc.ReadOnlyTemperatureController;
import mvc.ReadOnlyTemperatureModel;
import mvc.TemperatureControllerInterface;
import mvc.TemperatureModelInterface;

public class ReadOnlyTemperatureTest {

    public static void main (String[] args) {
        TemperatureModelInterface model = new ReadOnlyTemperatureModel();
        TemperatureControllerInterface controller = new ReadOnlyTemperatureController(model);
    }
}
