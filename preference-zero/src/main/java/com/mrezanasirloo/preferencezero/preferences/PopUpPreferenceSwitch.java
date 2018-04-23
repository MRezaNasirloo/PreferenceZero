package com.mrezanasirloo.preferencezero.preferences;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;

/**
 * Created by saeed on 4/11/2016.
 */
public class PopUpPreferenceSwitch extends EasyPreferenceSwitch {
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    public PopUpPreferenceSwitch(Context context) {
        super(context);
    }

    public PopUpPreferenceSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PopUpPreferenceSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void save(Boolean pref) {
        super.save(pref);
        if (pref)
            checkDrawPermission();
    }

    public void checkDrawPermission() {
        Context context = getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(context)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + context.getPackageName()));
                getContext().startActivity(intent);
            }
        }
    }
}
