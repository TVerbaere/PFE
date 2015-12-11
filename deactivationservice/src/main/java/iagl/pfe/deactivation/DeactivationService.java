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


public class DeactivationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Activity activity = Tools.getCurrentActivity();

        org.mozilla.javascript.Context c = org.mozilla.javascript.Context.enter();
        c.setOptimizationLevel(-1);

        RhinoScriptEngine engine = new RhinoScriptEngine();
        Reader script = recoverScript();

        try {
            engine.put("gui",new Facade(activity,c));
            engine.eval(script);

        } catch (Exception e) {
            e.printStackTrace();
        }

        org.mozilla.javascript.Context.exit();
        return 0;
    }

    /**
     * Get the javascript file reader.
     * @return
     */
    public Reader recoverScript() {
        InputStream is = getResources().openRawResource(R.raw.script);
        Reader reader = new InputStreamReader(is);
        return reader;
    }

}
