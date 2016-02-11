package iagl.pfe.deactivation;

import android.app.Application;
import android.util.Log;

import iagl.pfe.deactivation.util.LastEventSafeguard;

/**
 * CrashEventSender
 * @author T. VERBAERE
 * In progress ...
 */
public class CrashEventSender extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println(this.getClass().getName());

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

                Log.v(this.getClass().getName(), String.format(
                        "GUIEventNotification: [type] %s [class] %s [activity] %s [text] %s",
                        LastEventSafeguard.getEventTypeSafeguard(), LastEventSafeguard.getElementClassSafeguard(),
                        LastEventSafeguard.getActivitySafeguard(), LastEventSafeguard.getElementTextSafeguard()));
                Log.v(this.getClass().getName(), ex.getStackTrace().toString());
            }
        });
    }

}
