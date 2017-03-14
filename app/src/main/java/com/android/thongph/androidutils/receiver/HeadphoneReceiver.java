package com.android.thongph.androidutils.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Android Utils Created by thong on 2/10/2017.
 *
 * If you use this class, plz remember to declare BroadcastReceiver in your Android Manifests
 */

public class HeadphoneReceiver extends BroadcastReceiver {

    private static final String TAG = HeadphoneReceiver.class.getSimpleName();

    private Context context;

    /**
     * Default constructor
     */
    public HeadphoneReceiver() {
    }

    /**
     * Default constructor
     *
     * @param context - context
     */
    public HeadphoneReceiver(Context context) {
        this.context = context;
    }

    /**
     * State of headset
     */
    private static final int STATE_HEADSET_UNPLUGGED = 0;
    private static final int STATE_HEADSET_PLUGGED = 1;

    /**
     * Default values
     */
    private static final String STATE = "state";
    private static final int DEFAULT_STATE = -1;

    /**
     * Register headphone receiver
     */
    public void registerHeadphoneReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        context.registerReceiver(this, filter);
    }

    /**
     * Unregister headphone receiver
     */
    public void unregisterHeadphoneReceiver() {
        context.unregisterReceiver(this);
    }

    /**
     * Receiver
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra(STATE, DEFAULT_STATE);
            switch (state) {
                case STATE_HEADSET_UNPLUGGED:
                    Log.d(TAG, "Headset is unplugged.");
                    break;
                case STATE_HEADSET_PLUGGED:
                    Log.d(TAG, "Headset is plugged.");
                    break;
                default:
                    Log.d(TAG, "Can't identify headset state!");
            }
        }
    }
}
