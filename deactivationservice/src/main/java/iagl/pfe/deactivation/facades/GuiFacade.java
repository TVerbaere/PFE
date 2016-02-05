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
     * @return the view corresponding to the id.
     *
     * The return of this method is an Object. Indeed, this method is called in a Javascript script.
     * So the type "View" isn't compatible and we have to convert the view to an "Object".
     */
    public Object viewById(String id);

    /**
     * Gets the item located in a opened menu with an ID passed as parameter.
     * If no menu is opened, this function returns a null object.
     * @param iditem the id of the item to return
     * @return the view corresponding to the item.
     *
     * The return of this method is an Object. Indeed, this method is called in a Javascript script.
     * So the type "View" isn't compatible and we have to convert the view to an "Object".
     */
    public Object getmenuItemById(String iditem);

}
