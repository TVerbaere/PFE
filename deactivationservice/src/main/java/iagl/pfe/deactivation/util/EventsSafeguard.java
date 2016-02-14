package iagl.pfe.deactivation.util;

import android.app.Activity;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * EventsSafeguard
 * @author T. VERBAERE
 * Contains informations about GUI events.
 */
public class EventsSafeguard {

    private static List<AccessibilyEvent> events = new ArrayList<AccessibilyEvent>();


    /**
     * Stores an event and the activity where the event takes place.
     * @param clazz
     * @param text
     * @param type
     * @param place
     */
    public static void storeEvent(Activity place, String type, String clazz, String text) {
        events.add(new AccessibilyEvent(place, type, clazz, text));
    }

    /**
     *
     * @return
     */
    public static AccessibilyEvent getLastAccessibilityEvent() {
        return events.get(events.size()-1);
    }

    public static  List<AccessibilyEvent> getAllAccessibilitiesEvents() {
        return events;
    }


}
