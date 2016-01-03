package iagl.pfe.deactivation.facades;

import org.mozilla.javascript.Function;

/**
 * Interface of the Facade for Graphical User Interface
 * @author T. VERBAERE
 */
public interface GuiFacade {

    /**
     * Executes the function passed as second parameter only if the foreground activity
     * is the activity which name is the first parameter.
     * @param activity_name the name of the activity where function must be executed
     * @param function the function to execute
     */
    public void onActivity(String activity_name,Function function);

    /**
     * Gets the view with an ID passed as parameter.
     * @param id the id of the view to return
     */
    public Object viewById(String id);

}
