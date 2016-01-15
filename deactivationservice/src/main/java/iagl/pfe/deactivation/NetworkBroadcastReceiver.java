package iagl.pfe.deactivation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * NetworkBroadcastReceiver
 * @author T. VERBAERE
 * Thanks to this BroadcastReceiver, at each change of state, the service restart.
 */
public class NetworkBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Gets the foreground activity and starts the service
        Activity foregroundActivity = Tools.getCurrentActivity();
        Intent _intent = new Intent(foregroundActivity, DeactivationService.class);

        foregroundActivity.startService(_intent);

    }

}