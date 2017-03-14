package com.android.thongph.androidutils.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Android Utils
 * Created by thongph on 3/14/17.
 */

public class DisplayUtils {

    private Context context;

    /**
     * Default constructor
     * @param context - context
     */
    public DisplayUtils(Context context) {
        this.context = context;
    }

    /**
     * Get display metrics
     * @return - display metrics object
     */
    private DisplayMetrics getDisplayMetrics(){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * Get width pixels
     * @return - width pixels
     */
    public int getWidthPixels(){
        return getDisplayMetrics().widthPixels;
    }

    /**
     * Get height pixels
     * @return - height pixels
     */
    public int getHeightPixels(){
        return getDisplayMetrics().heightPixels;
    }

    /**
     * Get display density
     * @return - display density (xx dpi)
     */
    public float getDensityDpi(){
        return getDisplayMetrics().densityDpi * 160f;
    }
}
