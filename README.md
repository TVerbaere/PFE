# PFE 

[![Build Status](https://travis-ci.org/TVerbaere/PFE.svg)](https://travis-ci.org/TVerbaere/PFE)

How to add the deactivation service in an application ?

- First, add in the MANIFEST these lines :
```java

    <service class="iagl.pfe.deactivation.DeactivationService"
        android:name="iagl.pfe.deactivation.DeactivationService" >
    </service>

    <service
        android:name="iagl.pfe.deactivation.GUIEventNotification"
        android:enabled="true"
        android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE"
        android:label="SampleAccessibilityService" >
        <intent-filter>
            <action android:name="android.accessibilityservice.AccessibilityService" />
        </intent-filter>

        <meta-data
            android:name="android.accessibilityservice"
            android:resource="@xml/accessibillity_service_config" />
    </service>

    <receiver class="iagl.pfe.deactivation.NetworkBroadcastReceiver"
        android:name="iagl.pfe.deactivation.NetworkBroadcastReceiver">
        <intent-filter>
            <action android:name="iagl.pfe.START_SERVICE" />
            <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            <action android:name="android.net.wifi.WIFI_STATE_CHANGED" />
            <action android:name="android.bluetooth.adapter.action.STATE_CHANGED" />
            <action android:name="android.location.PROVIDERS_CHANGED" />
        </intent-filter>
    </receiver>
```

- Add this line in the tag appilcation
```java
    android:name="iagl.pfe.deactivation.CrashEventSender"
```

- Then, add this aspect in your application :
```java
    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.annotation.*;

    import iagl.pfe.deactivation.util.Tools;

    @Aspect
    public class DeactivationAspect {

            @Pointcut("execution(* *.onCreate(..))")
            public void onCreateEntryPoint() {
            }

            @Pointcut("call(* *.inflate(..))")
            public void inflateMenuEntryPoint() {
            }

            @AfterReturning(value= "inflateMenuEntryPoint() || onCreateEntryPoint()")
            public void startService(JoinPoint joinPoint) {
                Tools.treatJoinPoint(joinPoint);
            }

    }
```

- You should also add the dependency for AspectJ in build.gradle :
```java
    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath 'com.uphyca.gradle:gradle-android-aspectj-plugin:0.9.+'
        }
    }

    apply plugin: 'android-aspectj'
```

Actually the deactivation script is located [here](https://github.com/TVerbaere/PFE/blob/master/deactivationservice/src/main/res/raw/script.js)

Examples of script :
- To disable a view :
```javascript
    var gui = importing("gui");

    gui.viewById("@+id/button").setEnabled(false);

    // OR gui.viewById("button").setEnabled(false);
```
- To disable a menuItem :
```javascript
    var gui = importing("gui");

    gui.getmenuItemById("@+id/action_settings").setEnabled(false);

    // OR gui.viewById("action_settings").setEnabled(false);
```
- To disable a view in a specific activity :
```javascript
    var gui = importing("gui");

    gui.onActivity('MainActivity', function(){

        gui.viewById("@+id/button").setEnabled(false);

    });

```
- To disabled a view when the network is disabled :
```javascript
    var gui = importing("gui");
    var wifi = importing("wifi");

    gui.onActivity('MainActivity2', function(){
           if (!wifi.isConnected()) {
                  gui.viewById("@+id/button").setEnabled(false);
           }
           else {
                  gui.viewById("@+id/button").setEnabled(true);
           }
    });
```
- Others features
```javascript
    var battery = importing("battery");

    var level = battery.getLevel(); // Level of battery (ex : "56%")

    var gps = importing("gps");

    var gps_enabled = gps.isEnabled();

    var bluetooth = importing("bluetooth");

    var bluetooth_on = bluetooth.isOn();

```
#Progress
2 issues :
<br/>
1) The HistoryFacade isn't finished. This facade must be used to disable an element according to a scenario.
For example : <br/>
```javascript
    var history = importing("history");
    var gui = importing("gui");
    
    if (history.wrote("toto", "@+id/edittext", "MainActivity") && history.clicked("@+id/button", "MainActivity")) {
        gui.viewById("@+id/button2").setEnabled(false);
    }
    else {
        gui.viewById("@+id/button2").setEnabled(true);
    }
```
It's not possible to access to the id of the element clicked (or editText changed) in AccessibilityEvent. In an AccessibilityEvent, we can only access to a text. For a button, it's possible to use this data. But for an EditText, the text changes then this data isn't usable.
<br/>
2) The UncaughtExceptionHandler stops the app before execute the AccessibilityService for the last time. So the last event isn't stored in the list of events.
