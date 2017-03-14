package com.android.thongph.androidutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.android.thongph.androidutils.receiver.USBReceiver;

public class MainActivity extends AppCompatActivity {

    private USBReceiver usbReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // checking state of USB
        usbReceiver = new USBReceiver(this);
        usbReceiver.registerUSBReceiver();
        usbReceiver.getUsbDeviceInfo();
    }

    @Override
    protected void onStop() {
        usbReceiver.unRegisterUSBReceiver();
        super.onStop();
    }
}
