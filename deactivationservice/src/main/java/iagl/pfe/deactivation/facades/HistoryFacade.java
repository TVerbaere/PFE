package iagl.pfe.deactivation.facades;


/**
 * Interface of the Facade for old user interactions
 * @author T. VERBAERE
 */
public interface HistoryFacade {

    /**
     * Tests if the user wrote in the past a specific text in a specific view in a specific activity.
     * @param text the text wrote by the user
     * @param viewName the name of the view
     * @param activityName the name of the activity
     * @return true or false
     */
    public boolean wrote(String text, String viewName, String activityName);

    /**
     * Tests if the user clicked in the past in a specific view in a specific activity.
     * @param viewName the name of the view
     * @param activityName the name of the activity
     * @return true or false
     */
    public boolean clicked(String viewName, String activityName);

    /**
     * Tests if the user clicked (long) in the past in a specific view in a specific activity.
     * @param viewName the name of the view
     * @param activityName the name of the activity
     * @return true or false
     */
    public boolean longClicked(String viewName, String activityName);
}
