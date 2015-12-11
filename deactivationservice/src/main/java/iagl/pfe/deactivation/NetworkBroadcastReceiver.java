package iagl.pfe.deactivation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Activity foregroundActivity = Tools.getCurrentActivity();
        Intent _intent = new Intent(foregroundActivity, DeactivationService.class);

        foregroundActivity.startService(_intent);
        foregroundActivity.stopService(_intent);
    }


}
