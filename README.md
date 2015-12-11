# PFE


Pour l'instant pour utiliser le service :

-Dans le manifest de l'application, rajouter :

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


-Pour que le service fonctionne dès le chargement des activités, ajouter dans chaque onCreate :

        Intent _intent = new Intent(this, DeactivationService.class);
        startService(_intent);
        stopService(_intent);
