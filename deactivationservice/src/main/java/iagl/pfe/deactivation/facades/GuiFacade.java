package iagl.pfe.deactivation.facades;

import android.app.Activity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * Facade for Graphical User Interface
 * @author T. VERBAERE
 */
public class GuiFacade implements Facade{

    private Activity activity;
    private Context context;

    /**
     * Creates a new GUI facade.
     * @param act the foreground activity where modifications should take effect
     * @param c the runtime context for RhinoScriptEngine
     */
    public GuiFacade(Activity act, Context c) {
        this.activity = act;
        this.context = c;
    }

    /**
     * Executes the function passed as second parameter only if the foreground activity
     * is the activity which name is the first parameter.
     * @param activity_name the name of the activity where function must be executed
     * @param function the function to execute
     */
    public void onActivity(String activity_name,Function function) {
        // Compares activity_name and foreground activty name
        if ( activity.getLocalClassName().equals(activity_name) ) {
            // Initializes and calls function
            ScriptableObject scope = context.initStandardObjects();
            Scriptable that = context.newObject(scope);
            function.call(context, scope, that, new Object[]{});
        }
    }

    /**
     * Disables an item in foreground activity which id is passed as parameter.
     * @param id the id of the item to disable
     */
    public void disabledElementwithId(String id) {
        // Retrieves the true id in ressources
        int idt = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        // If the id is found, disable item
        if ( idt != 0 )
            activity.findViewById(idt).setEnabled(false);
    }

    /**
     * Enables an item in foreground activity which id is passed as parameter.
     * @param id the id of the item to enable
     */
    public void enabledElementwithId(String id) {
        // Retrieves the true id in ressources
        int idt = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        // If the id is found, enable item
        if ( idt != 0 )
            activity.findViewById(idt).setEnabled(true);
    }

    /*
    public void disabledMenuItemwithId(String itemname, String menuname) {
        NIY
        int idm = activity.getResources().getIdentifier(menuname, "menu", activity.getPackageName());
        int idt = activity.getResources().getIdentifier(itemname, "id", activity.getPackageName());
    }

    public void enabledMenuItemwithId(String itemname, String menuname) {
        NIY
        int idm = activity.getResources().getIdentifier(menuname, "menu", activity.getPackageName());
        int idt = activity.getResources().getIdentifier(itemname, "id", activity.getPackageName());
    }
    */
}
