# PFE 

[![Build Status](https://travis-ci.org/TVerbaere/PFE.svg)](https://travis-ci.org/TVerbaere/PFE)

How to add the deactivation service in an application ?

- First, add in the MANIFEST these lines :
```java
        <service class="iagl.pfe.deactivation.DeactivationService"
            android:name="iagl.pfe.deactivation.DeactivationService" >
        </service>

        <receiver class="iagl.pfe.deactivation.NetworkBroadcastReceiver"
            android:name="iagl.pfe.deactivation.NetworkBroadcastReceiver">
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
                <action android:name="android.net.wifi.WIFI_STATE_CHANGED" />
                <action android:name="android.bluetooth.adapter.action.STATE_CHANGED" />
                <action android:name="android.location.PROVIDERS_CHANGED" />
            </intent-filter>
        </receiver>
```
```java
- Then, in the onCreate function in activities, add these lines :

        Intent _intent = new Intent(this, DeactivationService.class);
        startService(_intent);
```
The deactivation script is located [here](https://github.com/TVerbaere/PFE/blob/master/deactivationservice/src/main/res/raw/script.js)

Examples of script :
- To disable a view :
```javascript
    var gui = importing("gui");

    gui.viewById("@+id/button").setEnabled(false);

    // OR gui.viewById("button").setEnabled(false);
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