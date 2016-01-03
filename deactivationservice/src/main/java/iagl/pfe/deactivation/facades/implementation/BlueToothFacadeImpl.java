package iagl.pfe.deactivation.facades.implementation;

import android.bluetooth.BluetoothAdapter;

import iagl.pfe.deactivation.facades.BluetoothFacade;
import iagl.pfe.deactivation.facades.Facade;

/**
 * Facade for BlueThooth
 * @author T. VERBAERE
 */
public class BlueToothFacadeImpl implements BluetoothFacade, Facade {

    /**
     * Creates a new BlueTooth facade.
     */
    public BlueToothFacadeImpl() {

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
