package iagl.pfe.deactivation.util;

import android.app.Activity;

/**
 * LastGUIEventSafeguard
 * @author T. VERBAERE
 * Contains informations about the last GUI event.
 */
public class LastEventSafeguard {

    private static Activity activity;
    private static String type;
    private static String clazz;
    private static String text;

    /**
     * Stores an event and the activity where the event takes place.
     * @param eventType
     * @param elementClass
     * @param elementText
     * @param place
     */
    public static void storeEvent(String eventType, String elementClass, String elementText, Activity place) {
        activity = place;
        type = eventType;
        clazz = elementClass;
        text = elementText;
    }

    /**
     *
     * @return
     */
    public static String getElementTextSafeguard() {
        return text;
    }

    /**
     *
     * @return
     */
    public static String getElementClassSafeguard() {
        return clazz;
    }

    /**
     *
     * @return
     */
    public static String getEventTypeSafeguard() {
        return type;
    }

    /**
     *
     * @return
     */
    public static Activity getActivitySafeguard() {
        return activity;
    }

}
