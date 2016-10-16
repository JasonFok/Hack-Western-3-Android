package com.example.jason.localdealsnotifier;

import android.bluetooth.BluetoothSocket;

/**
 * Created by David Vuong on 10/16/2016.
 */

public interface ConnectDeviceCallback {
    void processFinish(BluetoothSocket socket);
}
