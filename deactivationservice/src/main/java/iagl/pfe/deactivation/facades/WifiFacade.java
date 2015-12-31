package iagl.pfe.deactivation.facades;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Facade for Wifi
 * @author T. VERBAERE
 */
public class WifiFacade implements Facade {

    private Activity activity;

    /**
     * Creates a new Wifi facade.
     * @param act the foreground activity where modifications should take effect
     */
    public WifiFacade(Activity act) {
        this.activity = act;
    }

    /**
     * Tests if the network is connected.
     * @return a boolean, true if the network is connected otherwise false
     */
    public boolean isConnected() {
        // Gets connectivity manager and network informations
        ConnectivityManager conManager = (ConnectivityManager) activity.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();

        if( netInfo != null && netInfo.isConnected() )
            return true;

        else return false;
    }
}
