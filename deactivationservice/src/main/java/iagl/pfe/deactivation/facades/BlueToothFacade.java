package iagl.pfe.deactivation.facades;

/**
 * Interface of the Facade for BlueTooth
 * @author T. VERBAERE
 */
public interface BlueToothFacade {

    /**
     * Tests if the bluetooth is activated.
     * @return a boolean, true if bluetooth is activated otherwise false
     */
    public boolean isOn();

}
