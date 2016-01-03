package iagl.pfe.deactivation.facades;

/**
 * Interface of the Facade for Wifi
 * @author T. VERBAERE
 */
public interface WifiFacade {

    /**
     * Tests if the network is connected.
     * @return a boolean, true if the network is connected otherwise false
     */
    public boolean isConnected();
}
