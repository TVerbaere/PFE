package iagl.pfe.deactivation.facades;

/**
 * Interface of the Facade for Battery
 * @author T. VERBAERE
 */
public interface BatteryFacade {

    /**
     * Gets the level of the battery.
     * @return the level (percentage)
     */
    public String getLevel();

}
