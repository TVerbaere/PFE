package iagl.pfe.deactivation.util;

import android.content.Context;
import android.view.View;

/**
 * Placebo Object
 * @author T. VERBAERE
 * Null View returned when a view isn't found. His comportment is totally null.
 * Avoids Exceptions in deactivation script.
 */
public class Placebo extends View {

    public Placebo(Context context) {
        super(context);
    }
}
