package com.android.thongph.androidutils.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.support.compat.BuildConfig;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Android Utils
 * Created by thongph on 3/14/17.
 */

/**
 * Before using this class,
 * please declare <uses -feature android:name="android.hardware.usb.host"></uses>
 * & <receiver...></receiver...>
 * in your Manifests
 */
public class USBReceiver extends BroadcastReceiver {

    private static final String TAG = USBReceiver.class.getSimpleName();

    // ACTION USB PERMISSION
    private static final String ACTION_USB_PERMISSION =
        BuildConfig.APPLICATION_ID + ".USB_PERMISSION";

    private Context context;

    /**
     * Default constructor
     */
    public USBReceiver() {
    }

    /**
     * Default constructor
     *
     * @param context - context
     */
    public USBReceiver(Context context) {
        this.context = context;
    }

    /**
     * Register USB receiver
     */
    public void registerUSBReceiver(){
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        context.registerReceiver(this, filter);
    }

    /**
     * Unregister USB receiver
     */
    public void unRegisterUSBReceiver(){
        context.unregisterReceiver(this);
    }

    /**
     * Get USB device info
     */
    public void getUsbDeviceInfo() {
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        /*
         * This block required if you need to communicate to USB devices
         * it's take permission to device
         * If you want than you can set this to which device you want to communicate
         */
        PendingIntent permissionIntent = PendingIntent
            .getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);

        // get USB device info
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        String usbDeviceInfo = "";
        while (deviceIterator.hasNext()) {
            UsbDevice usbDevice = deviceIterator.next();
            usbManager.requestPermission(usbDevice, permissionIntent);
            usbDeviceInfo += "\n" + "DeviceID: " + usbDevice.getDeviceId() + "\n"
                + "DeviceName: " + usbDevice.getDeviceName() + "\n"
                + "DeviceClass: " + usbDevice.getDeviceClass() + "\n"
                + "DeviceSubClass: " + usbDevice.getDeviceSubclass() + "\n"
                + "VendorID: " + usbDevice.getVendorId() + "\n"
                + "ProductID: " + usbDevice.getProductId() + "\n";
        }
        Log.d(TAG, "USB Device Info: " + usbDeviceInfo);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_USB_PERMISSION)) {
            synchronized (context) {
                UsbDevice usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    if (usbDevice != null) {
                        //TODO call method to set up device communication
                        Log.d(TAG, "USB device !=null");
                    }
                } else {
                    Log.d(TAG, "Permission denied for device " + usbDevice);
                }
            }
        }
    }
}
