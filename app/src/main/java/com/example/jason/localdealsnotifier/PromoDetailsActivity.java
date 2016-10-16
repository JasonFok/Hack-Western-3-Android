package com.example.jason.localdealsnotifier;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class PromoDetailsActivity extends AppCompatActivity implements ConnectDeviceCallback {

    public static final String COMPANY_DATA = "companyData";
    public static final String MESSAGE_DATA = "messageData";
    public static final String DISTANCE_DATA = "distanceData";
    private final int REQUEST_ENABLE_BT = 1;
    private final String DEVICE_NAME = "HC-06";
    private BluetoothAdapter mBluetoothAdapter;
    private DeviceController deviceController = null;

    private String company;
    private String message;
    private String distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_details);

        if (getIntent() != null) {
            company = getIntent().getStringExtra(COMPANY_DATA);
            message = getIntent().getStringExtra(MESSAGE_DATA);
            distance = getIntent().getStringExtra(DISTANCE_DATA);
            Log.d("test", company + " " + message + " " + distance);
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_promo_details);
        TextView companyTextView = (TextView)linearLayout.findViewById(R.id.companyD);
        TextView messageTextView = (TextView)linearLayout.findViewById(R.id.messageD);
        TextView distanceTextView = (TextView)linearLayout.findViewById(R.id.distanceD);

        companyTextView.setText(company);
        messageTextView.setText(message);
        distanceTextView.setText(distance);

        setUpBluetooth();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (deviceController != null) {
            deviceController.close();
        }
    }

    private void setUpBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            finish();
        }
        else {
            //has bluetooth!
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            else {
                getRecognizedDevices();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {

                Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT);
                getRecognizedDevices();
            }
            else {
                Toast.makeText(getApplicationContext(), "Not Working", Toast.LENGTH_SHORT);
            }
        }
    }

    private void getRecognizedDevices() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        ArrayList<String> devices = new ArrayList<>();

        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                devices.add(device.getName() + "\n" + device.getAddress());

                if (DEVICE_NAME.equals(device.getName())) {
                    connectToDevice(device);
                }
            }
        }

        else {
            Toast.makeText(getApplicationContext(), "Cannot find device.", Toast.LENGTH_SHORT);
        }
    }

    private void connectToDevice(BluetoothDevice device) {
        ConnectDevice obj = new ConnectDevice(device.getName(), device.getAddress(), getApplicationContext(), this);
        obj.execute();
    }

    @Override
    public void processFinish(BluetoothSocket socket) {
        deviceController = new DeviceController(socket, getApplicationContext(), message);
        try {
            deviceController.read();
        }
        catch (IOException ex) {
            finish();
        }
        //you can send data now
    }
}
