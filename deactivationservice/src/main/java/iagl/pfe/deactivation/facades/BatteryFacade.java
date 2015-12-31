package iagl.pfe.deactivation.facades;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;


/**
 * Facade for Battery
 * @author T. VERBAERE
 */
public class BatteryFacade implements Facade {

    private Activity activity;

    /**
     * Creates a new Battery facade.
     * @param act the foreground activity where modifications should take effect
     */
    public BatteryFacade(Activity act) {
        this.activity = act;
    }

    /**
     * Gets the level of the battery.
     * @return the level (percentage)
     */
    public String getLevel() {
        Intent intent  = activity.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int    level   = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int    scale   = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int    percent = (level*100)/scale;
        return String.valueOf(percent) + "%";
    }

}
