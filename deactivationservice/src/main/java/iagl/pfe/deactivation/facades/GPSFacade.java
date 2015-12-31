package iagl.pfe.deactivation.facades;

import android.app.Activity;
import android.location.LocationManager;

/**
 * Facade for GPS
 * @author T. VERBAERE
 */
public class GPSFacade implements Facade {

    private Activity activity;

    /**
     * Creates a new GPS facade.
     * @param act the foreground activity where modifications should take effect
     */
    public GPSFacade(Activity act) {
        this.activity = act;
    }

    /**
     * Tests if the GPS is enabled.
     * @return a boolean, true if the GPS is enabled otherwise false
     */
    public boolean isGPSEnabled(){
        LocationManager locManager = (LocationManager) activity.getSystemService(android.content.Context.LOCATION_SERVICE);
        return locManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
    }
}
