package iagl.pfe.deactivation.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.ArrayMap;
import android.view.Menu;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Tools
 * @author T. VERBAERE
 * Contains usefull methods.
 */
public class Tools {

    public static Menu menu = null;

    /**
     * Gets the foreground activity.
     *
     * @return the activity which is running
     */
    public static Activity getCurrentActivity() {
        try {
            // Retrieves the class which manage the main application thread
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            // Invokes the method "currentActivityThread" to gets the current activity
            Object currentActivityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            // Retrieves the field mActivities
            Field mActivities = activityThreadClass.getDeclaredField("mActivities");
            // Makes accessible the field mActivities
            mActivities.setAccessible(true);
            // Retrieves all activity which are executed in the main thread

            HashMap activities = null;

            if (Build.VERSION.SDK_INT >= 19) {
                activities = new HashMap((ArrayMap)mActivities.get(currentActivityThread));
            }
            else {
                activities = (HashMap)mActivities.get(currentActivityThread);
            }

            for (Object activityRecord : activities.values()) {
                // Retrieves the class of the record
                Class activityRecordClass = activityRecord.getClass();
                // If it's the foreground activity, the field paused is false
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                // Makes accessible the field paused
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    // If it's the foreground activity, retrieves the field  containing the activity
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    // Makes accessible the field activity
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    // Return the activity
                    return activity;
                }
            }

        } catch (final Exception e) {

        }

        return null;
    }

    /**
     * Performs the processing according to the joinpoint passed as parameter.
     * @param jp the joinPoint to treat
     */
    public static void treatJoinPoint(JoinPoint jp) {
        // If the joinPoint contains two arguments, then we want to disable a menu (call : inflate)
        if(jp.getArgs().length==2) {
            // The menu is the second argument
            Menu m = (Menu) jp.getArgs()[1];
            if (m != null) {
                menu = m;
            }
        }

        // Sends START_SERVICE Action
        Activity a = (Activity) jp.getThis();
        Intent intent = new Intent();
        intent.setAction("iagl.pfe.START_SERVICE");
        a.sendBroadcast(intent);
    }

}

