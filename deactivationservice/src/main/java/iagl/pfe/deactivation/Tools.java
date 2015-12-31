package iagl.pfe.deactivation;

import android.app.Activity;
import android.util.ArrayMap;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Tools
 * @author T. VERBAERE
 * Contains usefull methods.
 */
public class Tools {

    /**
     * Gets the foreground activity.
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
            HashMap activities =  new HashMap((ArrayMap)mActivities.get(currentActivityThread));

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

}

