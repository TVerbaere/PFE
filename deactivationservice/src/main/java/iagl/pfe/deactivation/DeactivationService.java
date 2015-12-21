package iagl.pfe.deactivation;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;

import com.sun.script.javascript.RhinoScriptEngine;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Deactivation Service
 * @author T. VERBAERE
 * Service to disable items of an Android app.
 */
public class DeactivationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Retrieves the foreground activity
        Activity activity = Tools.getCurrentActivity();

        // Creates a runtime context to execute scripts with Rhino
        org.mozilla.javascript.Context c = org.mozilla.javascript.Context.enter();
        // Sets the optimization level to -1 (so the interpretive mode will always be used)
        c.setOptimizationLevel(-1);

        // Creates the RhinoScriptEngine
        RhinoScriptEngine engine = new RhinoScriptEngine();
        Reader script = retrieveScript(); // Temporary ! Gets a script only for tests

        try {
            // Injects the facade instead of "gui"
            engine.put("gui",new Facade(activity,c));
            // Evaluates script with injected facade
            engine.eval(script);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Exits context
        org.mozilla.javascript.Context.exit();
        return 0;
    }

    /**
     * Get the javascript file reader (Temporary).
     * @return the reader of the deactivation script.
     */
    public Reader retrieveScript() {
        InputStream is = getResources().openRawResource(R.raw.script);
        Reader reader = new InputStreamReader(is);
        return reader;
    }

}
