package iagl.pfe.deactivation;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import iagl.pfe.deactivation.util.EventsSafeguard;
import iagl.pfe.deactivation.util.Tools;

/**
 * Accessibility Service
 * @author T. VERBAERE
 * Service to be notified of GUI Events.
 */
public class GUIEventNotification extends AccessibilityService {

    /**
     * Gets the name of the event type.
     * @param event the event
     * @return the name of the event type
     */
    private String getEventType(AccessibilityEvent event) {
        return AccessibilityEvent.eventTypeToString(event.getEventType());
    }

    /**
     * Gets the text of the event type.
     * @param event the event
     * @return the text of the event type
     */
    private String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);
        }
        return sb.toString();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Activity a = Tools.getCurrentActivity();
        // Stores the accessibility event
        EventsSafeguard.storeEvent(a, getEventType(event), event.getClassName().toString(), getEventText(event));

        // Log :
        Log.v(this.getClass().getName(), String.format(
                "GUIEventNotification: [type] %s [class] %s [activity] %s [text] %s",
                EventsSafeguard.getLastAccessibilityEvent().getEventType(), EventsSafeguard.getLastAccessibilityEvent().getClassName(),
                EventsSafeguard.getLastAccessibilityEvent().getSource(), EventsSafeguard.getLastAccessibilityEvent().getEventText()));

        // We can also start the deactivation service in this method...
        if (a != null && (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED ||
                event.getEventType() == AccessibilityEvent.TYPE_VIEW_LONG_CLICKED ||
                event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED ||
                event.getEventType() == AccessibilityEvent.TYPE_VIEW_SELECTED)) {

            Intent intent = new Intent();
            intent.setAction("iagl.pfe.START_SERVICE");
            a.sendBroadcast(intent);
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
    }
}
