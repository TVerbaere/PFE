package iagl.pfe.deactivation.util;

import android.app.Activity;

/**
 * Accessibility Event
 * @author T. VERBAERE
 * Class representing a accessibility event.
 */
public class AccessibilyEvent {

    private Activity location;
    private String typeEvent;
    private String clazzEvent;
    private String textEvent;

    /**
     * Create an accessibility event
     * @param clazz the class of the problematic object
     * @param text the text of the problematic object
     * @param type the type of the accessibility event
     * @param activity the activity where the event takes place
     */
    public AccessibilyEvent(Activity activity, String type, String clazz, String text) {
        location = activity;
        typeEvent = type;
        textEvent = text;
        clazzEvent = clazz;
    }

    /**
     * Gets the activity where the activity takes place
     * @return the activity
     */
    public Activity getSource() {
        return location;
    }

    /**
     * Gets the type of the accessibility event
     * @return the type of the accessibility event
     */
    public String getEventType() {
        return typeEvent;
    }

    /**
     * Gets the class of the problematic object
     * @return the class of the problematic object
     */
    public String getClassName() {
        return clazzEvent;
    }

    /**
     * Gets the text of the problematic object
     * @return the text of the problematic object
     */
    public String getEventText() {
        return textEvent;
    }

}
