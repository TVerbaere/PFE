package iagl.pfe.deactivation;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import iagl.pfe.deactivation.util.LastEventSafeguard;
import iagl.pfe.deactivation.util.Tools;

/**
 * Accessibility Service
 * @author T. VERBAERE
 * Service to be notified of GUI Events.
 * In progress ...
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

        LastEventSafeguard.storeEvent(getEventType(event), event.getClassName().toString(), getEventText(event), Tools.getCurrentActivity());

        Log.v(this.getClass().getName(), String.format(
                "GUIEventNotification: [type] %s [class] %s [activity] %s [text] %s",
                LastEventSafeguard.getEventTypeSafeguard(), LastEventSafeguard.getElementClassSafeguard(),
                LastEventSafeguard.getActivitySafeguard(), LastEventSafeguard.getElementTextSafeguard()));
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
    }
}
