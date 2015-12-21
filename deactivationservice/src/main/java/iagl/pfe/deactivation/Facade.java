package iagl.pfe.deactivation;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.util.Log;
import android.view.Window;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.lang.reflect.Field;

/**
 * Facade
 * @author T. VERBAERE
 * Facade injected in script to disable items.
 */
public class Facade {

    private Activity activity;
    private Context context;

    /**
     * Creates a new facade.
     * @param act the foreground activity where modifications should take effect
     * @param c the runtime context for RhinoScriptEngine
     */
    public Facade(Activity act, Context c) {
        this.activity = act;
        this.context = c;
    }

    /**
     * Executes the function passed as second parameter only if the foreground activity
     * is the activity which name is the first parameter.
     * @param activity_name the name of the activity where function must be executed
     * @param function the function to execute
     */
    public void onActivity(String activity_name,Function function) {
        // Compares activity_name and foreground activty name
        if ( activity.getLocalClassName().equals(activity_name) ) {
            // Initializes and calls function
            ScriptableObject scope = context.initStandardObjects();
            Scriptable that = context.newObject(scope);
            function.call(context, scope, that, new Object[]{});
        }
    }

    /**
     * Disables an item of a menu.
     * @param id the id of the item to disable
     * @param menu_id the id of the menu which item is located ?
     */
    public void disabledMenuItemwithId(String id) {

        /*
        try {
            Object o = activity.getWindow();
            Field f = o.getClass().getDeclaredField("mPanels");
            f.setAccessible(true);
            Object o2 = f.get(o);

            System.out.println(o2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

    /**
     * Enables an item of a menu.
     * @param id the id of the item to enable
     * @param menu_id the id of the menu which item is located ?
     */
    public void enabledMenuItemwithId(String id) {

    }

    /**
     * Disables an item in foreground activity which id is passed as parameter.
     * @param id the id of the item to disable
     */
    public void disabledElementwithId(String id) {
        // Retrieves the true id in ressources
        int idt = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        // If the id is found, disable item
        if ( idt != 0 )
            activity.findViewById(idt).setEnabled(false);
    }

    /**
     * Enables an item in foreground activity which id is passed as parameter.
     * @param id the id of the item to enable
     */
    public void enabledElementwithId(String id) {
        // Retrieves the true id in ressources
        int idt = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        // If the id is found, enable item
        if ( idt != 0 )
            activity.findViewById(idt).setEnabled(true);
    }

    /**
     * Tests if the network is connected.
     * @return a boolean, true if the network is connected otherwise false
     */
    public boolean isNetworkConnected() {
        // Gets connectivity manager and network informations
        ConnectivityManager conManager = (ConnectivityManager) activity.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();

        if( netInfo != null && netInfo.isConnected() )
            return true;

        else return false;
    }

    /**
     * Tests if the GPS is enabled.
     * @return a boolean, true if the GPS is enabled otherwise false
     */
    public boolean isGPSEnabled(){
        LocationManager locManager = (LocationManager) activity.getSystemService(android.content.Context.LOCATION_SERVICE);
        return locManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
    }

    /**
     * Tests if the bluetooth is activated.
     * @return a boolean, true if bluetooth is activated otherwise false
     */
    public boolean isBlueToothOn(){
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        return btAdapter != null && btAdapter.isEnabled();
    }

    /**
     * Gets the level of the battery.
     * @return the level (percentage)
     */
    public String getBatteryLevel() {
        Intent intent  = activity.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int    level   = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int    scale   = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int    percent = (level*100)/scale;
        return String.valueOf(percent) + "%";
    }

}
