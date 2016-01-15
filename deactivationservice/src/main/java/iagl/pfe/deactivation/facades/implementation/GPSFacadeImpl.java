package iagl.pfe.deactivation.facades.implementation;

import android.app.Activity;
import android.location.LocationManager;

import iagl.pfe.deactivation.facades.Facade;
import iagl.pfe.deactivation.facades.GPSFacade;

/**
 * Facade for GPS
 * @author T. VERBAERE
 */
public class GPSFacadeImpl implements GPSFacade, Facade {

    private Activity activity;

    /**
     * Creates a new GPS facade.
     * @param act the foreground activity where modifications should take effect
     */
    public GPSFacadeImpl(Activity act) {
        this.activity = act;
    }

    /**
     * Tests if the GPS is enabled.
     * @return a boolean, true if the GPS is enabled otherwise false
     */
    public boolean isEnabled(){
        LocationManager locManager = (LocationManager) activity.getSystemService(android.content.Context.LOCATION_SERVICE);
        return locManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
    }
}
