package iagl.pfe.deactivation;

import android.app.Application;
import android.util.Log;

import iagl.pfe.deactivation.util.EventsSafeguard;

/**
 * CrashEventSender
 * @author T. VERBAERE
 * Class to save accessibility event to create a good trace for crashes.
 */
public class CrashEventSender extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Defines a UncaughtExceptionHandler to catch the crash
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

                // Log :
                Log.v(this.getClass().getName(), String.format(
                        "LastGUIEvent: [type] %s [class] %s [activity] %s [text] %s",
                        EventsSafeguard.getLastAccessibilityEvent().getEventType(), EventsSafeguard.getLastAccessibilityEvent().getClassName(),
                        EventsSafeguard.getLastAccessibilityEvent().getSource(), EventsSafeguard.getLastAccessibilityEvent().getEventText()));

                // Log the exception :
                for (StackTraceElement ste : ex.getStackTrace()) {
                    Log.v("Exception", ste.toString());
                }

                //System.exit(2);  <- decomment to quit the app after the crash
            }
        });
    }

}
