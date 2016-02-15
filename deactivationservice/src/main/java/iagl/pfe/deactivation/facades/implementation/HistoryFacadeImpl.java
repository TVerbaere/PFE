package iagl.pfe.deactivation.facades.implementation;

import java.util.List;

import iagl.pfe.deactivation.facades.Facade;
import iagl.pfe.deactivation.facades.HistoryFacade;
import iagl.pfe.deactivation.util.AccessibilyEvent;
import iagl.pfe.deactivation.util.EventsSafeguard;

/**
 * Facade for old user interactions
 * @author T. VERBAERE
 */
public class HistoryFacadeImpl implements HistoryFacade, Facade {

    public HistoryFacadeImpl() {}

    /**
     * Tests if the user wrote in the past a specific text in a specific view in a specific activity.
     * @param text the text wrote by the user
     * @param viewName the name of the view (not the id)
     * @param activityName the name of the activity
     * @return true or false
     */
    public boolean wrote(String text, String viewName, String activityName) {

        // No through way ! we don't know the id of the view in AccessibilityEvent !
        // For others methods, it's possible to take the text in the event because it's not editable. Not here !

        return false;
    }


    /**
     * Tests if the user clicked in the past in a specific view in a specific activity.
     * @param viewName the name of the view (not the id)
     * @param activityName the name of the activity
     * @return true or false
     */
    public boolean clicked(String viewName, String activityName) {

        List<AccessibilyEvent> events = EventsSafeguard.getAllAccessibilitiesEvents();

        for (AccessibilyEvent event : events) {
            if (event.getSource().getLocalClassName().equals(activityName) && event.getEventType().equals("TYPE_VIEW_CLICKED")
                    && event.getEventText().equals(viewName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests if the user clicked (long) in the past in a specific view in a specific activity.
     * @param viewName the name of the view (not the id)
     * @param activityName the name of the activity
     * @return true or false
     */
    public boolean longClicked(String viewName, String activityName) {

        List<AccessibilyEvent> events = EventsSafeguard.getAllAccessibilitiesEvents();

        for (AccessibilyEvent event : events) {
            if (event.getSource().getLocalClassName().equals(activityName) && event.getEventType().equals("TYPE_VIEW_LONG_CLICKED")
                    && event.getEventText().equals(viewName)) {
                return true;
            }
        }

        return false;
    }
}
