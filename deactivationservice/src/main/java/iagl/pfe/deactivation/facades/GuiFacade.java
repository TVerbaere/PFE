package iagl.pfe.deactivation.facades;

import android.app.Activity;
import android.view.View;

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
     * Gets the view with an ID passed as parameter.
     * @param id the id of the view to return
     */
    public Object viewById(String id) {
        // If the id starts with "@+id", delete the subsequence to find view with getRessources().
        if (id.startsWith("@+id/"))
            id = id.substring(5);

        // Initializes the scope.
        ScriptableObject scope = context.initStandardObjects();
        View v = null;

        // Retrieves the true id in ressources.
        int idt = activity.getResources().getIdentifier(id, "id", activity.getPackageName());
        // If the id is found, enable item.
        if ( idt != 0 )
            v = activity.findViewById(idt);

        // convert JAVA object to JavaScript object
        return Context.javaToJS(v, scope);
    }

}
