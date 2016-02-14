package iagl.pfe.deactivation.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * EventsSafeguard
 * @author T. VERBAERE
 * Contains informations about GUI events.
 */
public class EventsSafeguard {

    // List of events
    private static List<AccessibilyEvent> events = new ArrayList<AccessibilyEvent>();

    /**
     * Stores an event and the activity where the event takes place.
     * @param clazz the class of the problematic object
     * @param text the text of the problematic object
     * @param type the type of the accessibility event
     * @param place the activity where the event takes place
     */
    public static void storeEvent(Activity place, String type, String clazz, String text) {
        events.add(new AccessibilyEvent(place, type, clazz, text));
    }

    /**
     * Gets the last accessibility event plays
     * @return the last accessibility event
     */
    public static AccessibilyEvent getLastAccessibilityEvent() {
        return events.get(events.size()-1);
    }

    /**
     * Gets all accessibilities events play
     * @return the list of all accessibilities events
     */
    public static  List<AccessibilyEvent> getAllAccessibilitiesEvents() {
        return events;
    }


}
