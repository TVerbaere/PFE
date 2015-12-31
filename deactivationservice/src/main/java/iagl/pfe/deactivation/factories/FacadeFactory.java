package iagl.pfe.deactivation.factories;

import android.app.Activity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import iagl.pfe.deactivation.facades.BatteryFacade;
import iagl.pfe.deactivation.facades.BlueToothFacade;
import iagl.pfe.deactivation.facades.Facade;
import iagl.pfe.deactivation.facades.GPSFacade;
import iagl.pfe.deactivation.facades.GuiFacade;
import iagl.pfe.deactivation.facades.WifiFacade;

/**
 * Facade Factory
 * @author T. VERBAERE
 * Facade injected in the script which import alone the adapted facade.
 */
public class FacadeFactory {

    private Activity activity;
    private Context context;

    /**
     * Creates a new factory facade.
     * @param act the foreground activity where modifications should take effect
     * @param c the runtime context for RhinoScriptEngine
     */
    public FacadeFactory(Activity act, Context c) {
        this.activity = act;
        this.context = c;
    }

    /**
     * Imports the facade which name is specified as parameter.
     * @param facadename the facade name
     * @return the instance of facade
     */
    public Object importing(String facadename) {

        // Initializes the scope.
        ScriptableObject scope = context.initStandardObjects();
        Facade facadeUsed = null;

        // Returns the adapted facade
        if (facadename.equals("gui"))
            facadeUsed =  new GuiFacade(this.activity, this.context);

        if (facadename.equals("wifi"))
            facadeUsed = new WifiFacade(this.activity);

        if (facadename.equals("gps"))
            facadeUsed = new GPSFacade(this.activity);

        if (facadename.equals("bluetooth"))
            facadeUsed = new BlueToothFacade();

        if (facadename.equals("battery"))
            return new BatteryFacade(this.activity);

        // convert JAVA object to JavaScript object
        Object jsObj = Context.javaToJS(facadeUsed, scope);

        return jsObj;
    }
}
