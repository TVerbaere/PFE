package iagl.pfe.deactivation;

import android.app.Application;
import android.util.Log;

import iagl.pfe.deactivation.util.EventsSafeguard;

/**
 * CrashEventSender
 * @author T. VERBAERE
 * In progress ...
 */
public class CrashEventSender extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

                Log.v(this.getClass().getName(), String.format(
                        "LastGUIEvent: [type] %s [class] %s [activity] %s [text] %s",
                        EventsSafeguard.getLastAccessibilityEvent().getEventType(), EventsSafeguard.getLastAccessibilityEvent().getClassName(),
                        EventsSafeguard.getLastAccessibilityEvent().getSource(), EventsSafeguard.getLastAccessibilityEvent().getEventText()));

                for (StackTraceElement ste : ex.getStackTrace()) {
                    Log.v("Exception", ste.toString());
                }

                //System.exit(2);
            }
        });
    }

}
