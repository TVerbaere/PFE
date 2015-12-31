package iagl.pfe.deactivation.facades;

import android.bluetooth.BluetoothAdapter;

/**
 * Facade for BlueThooth
 * @author T. VERBAERE
 */
public class BlueToothFacade implements Facade {

    /**
     * Creates a new BlueTooth facade.
     */
    public BlueToothFacade() {

    }

    /**
     * Tests if the bluetooth is activated.
     * @return a boolean, true if bluetooth is activated otherwise false
     */
    public boolean isOn(){
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        return btAdapter != null && btAdapter.isEnabled();
    }
}
