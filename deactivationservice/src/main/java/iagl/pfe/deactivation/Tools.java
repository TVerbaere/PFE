package iagl.pfe.deactivation;

import android.app.Activity;
import android.util.ArrayMap;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Thibaud on 20/11/2015.
 */
public class Tools {

    /**
     * Get the foreground activity.
     * @return
     */
    public static Activity getCurrentActivity() {
        try {
            // On récupère la classe qui permet de gérer le thread principal de l'application :
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            // De cette classe on invoke la méthode retournant le thread principal :
            Object currentActivityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            // On récupère un ArrayMap contenant les activités :
            Field mActivities = activityThreadClass.getDeclaredField("mActivities");
            // Pour pouvoir récupèrer le champs on doit le rendre accessible :
            mActivities.setAccessible(true);
            // On récupère les activités qui s'exécutent dans le thread principal :
            HashMap activities =  new HashMap((ArrayMap)mActivities.get(currentActivityThread));

            // On va tous les parcourir :
            for (Object activityRecord : activities.values()) {
                // On récupère la classe correspondante à chaque élément contenu dans la map :
                Class activityRecordClass = activityRecord.getClass();
                // L'activité que nous recherchons n'est pas en pause, on va donc regarder la valeur du champs "paused" :
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                // On va aussi le rendre accessible pour éviter une exception :
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    // Si l'element n'est pas en pause, alors c'est l'activité qui est en cours d'exécution,
                    // On va donc récupèrer le champs "activity" qui contient la valeur qui nous intéresse :
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    // Il faut rendre accessible ce champs :
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }

        } catch (final Exception e) {

        }

        return null;
    }

}
