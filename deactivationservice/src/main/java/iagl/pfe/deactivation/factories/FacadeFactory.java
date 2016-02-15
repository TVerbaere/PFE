package iagl.pfe.deactivation.factories;

import android.app.Activity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import iagl.pfe.deactivation.facades.HistoryFacade;
import iagl.pfe.deactivation.facades.implementation.BatteryFacadeImpl;
import iagl.pfe.deactivation.facades.implementation.BlueToothFacadeImpl;
import iagl.pfe.deactivation.facades.Facade;
import iagl.pfe.deactivation.facades.implementation.GPSFacadeImpl;
import iagl.pfe.deactivation.facades.implementation.GuiFacadeImpl;
import iagl.pfe.deactivation.facades.implementation.HistoryFacadeImpl;
import iagl.pfe.deactivation.facades.implementation.WifiFacadeImpl;

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
            facadeUsed =  new GuiFacadeImpl(this.activity, this.context);

        if (facadename.equals("wifi"))
            facadeUsed = new WifiFacadeImpl(this.activity);

        if (facadename.equals("gps"))
            facadeUsed = new GPSFacadeImpl(this.activity);

        if (facadename.equals("bluetooth"))
            facadeUsed = new BlueToothFacadeImpl();

        if (facadename.equals("battery"))
            facadeUsed = new BatteryFacadeImpl(this.activity);

        if (facadename.equals("history"))
            facadeUsed = new HistoryFacadeImpl();

        // convert JAVA object to JavaScript object
        Object jsObj = Context.javaToJS(facadeUsed, scope);

        return jsObj;
    }
}
