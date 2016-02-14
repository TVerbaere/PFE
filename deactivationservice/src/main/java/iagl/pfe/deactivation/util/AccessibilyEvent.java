package iagl.pfe.deactivation.util;

import android.app.Activity;

public class AccessibilyEvent {

    private Activity location;
    private String typeEvent;
    private String clazzEvent;
    private String textEvent;


    public AccessibilyEvent(Activity activity, String type, String clazz, String text) {
        location = activity;
        typeEvent = type;
        textEvent = text;
        clazzEvent = clazz;
    }

    public Activity getSource() {
        return location;
    }

    public String getEventType() {
        return typeEvent;
    }

    public String getClassName() {
        return clazzEvent;
    }

    public String getEventText() {
        return textEvent;
    }

}
