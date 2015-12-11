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


public class Facade {

    private Activity activity;
    private Context context;

    public Facade(Activity act, Context c) {
        this.activity = act;
        this.context = c;
    }

    public void onActivity(String activity_name,Function function) {

        if ( activity.getLocalClassName().equals(activity_name) ) {
            ScriptableObject scope = context.initStandardObjects();
            Scriptable that = context.newObject(scope);
            function.call(context, scope, that, new Object[]{});
        }
    }

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

    public void enabledMenuItemwithId(String id) {

    }

    public void disabledElementwithId(String id) {
        int idt = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        if ( idt != 0 )
            activity.findViewById(idt).setEnabled(false);
    }

    public void enabledElementwithId(String id) {
        int idt = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        if ( idt != 0 )
            activity.findViewById(idt).setEnabled(true);
    }

    public boolean isNetworkConnected() {

        ConnectivityManager conManager = (ConnectivityManager) activity.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();

        if( netInfo != null && netInfo.isConnected() )
            return true;

        else return false;
    }

    public boolean isGPSEnabled(){
        LocationManager locManager = (LocationManager) activity.getSystemService(android.content.Context.LOCATION_SERVICE);
        return locManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
    }

    public boolean isBlueToothOn(){
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        return btAdapter != null && btAdapter.isEnabled();
    }

    public String getBatteryLevel() {
        Intent intent  = activity.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int    level   = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int    scale   = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int    percent = (level*100)/scale;
        return String.valueOf(percent) + "%";
    }

}
