package iagl.pfe.deactivation;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.sun.script.javascript.RhinoScriptEngine;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import iagl.pfe.deactivation.factories.FacadeFactory;

/**
 * Deactivation Service
 * @author T. VERBAERE
 * Service to disable items in an Android app.
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
            // Transforms the reader to a string
            char[] arr = new char[8 * 1024];
            StringBuilder buffer = new StringBuilder();
            int value;
            while ((value = script.read(arr, 0, arr.length)) != -1) {
                buffer.append(arr, 0, value);
            }
            script.close();
            String script2str = buffer.toString();

            // If the script is passed as extraData, affect this value (works for test)
            if (intent.getStringExtra("script") != null)
                script2str = intent.getStringExtra("script");

            // Replaces importing(that) to self.importing(that) because we cannot replace expressions with the engine,
            // only replace objects.
            script2str = script2str.replaceAll("importing\\(\\\"([a-z]*)\\\"\\);","self.importing(\"$1\");");

            // Injects facades instead of "self"
            engine.put("self", new FacadeFactory(activity, c));

            // Evaluates script with injected facade
            engine.eval(script2str);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Exits context
        org.mozilla.javascript.Context.exit();
        return 0;
    }

    /**
     * Gets the javascript file reader.
     * The Script is located in the resources, in raw folder.
     * @return the file reader
     */
    public Reader retrieveScript() {
        InputStream is = getResources().openRawResource(R.raw.script);
        Reader reader = new InputStreamReader(is);
        return reader;
    }

}
