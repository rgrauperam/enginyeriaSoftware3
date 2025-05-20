package mvc;

public class StandardTemperatureController{
    /*TODO: Ensure that it implements the right interface*/

    TemperatureModelInterface model;
    TemperatureView view;

    public StandardTemperatureController(TemperatureModelInterface model){
        /*TODO: Create view and assign class attributes*/
        view.createView();
        view.createControls();
        /*TODO: Prepare UI */
    }

    /*TODO: Complete with the interface methods. Some tips below.*/

    /*TODO: Start method is invoked when the user clicks File->Start
    * It should invoke model.on
    * It should also prepare the UI, enabling and disabling the right elements
    *
    *  */


    /*TODO: Start method is invoked when the user clicks File->Stop
     * It should invoke model.off
     * It should also prepare the UI, enabling and disabling the right elements
     *
     *  */



}
