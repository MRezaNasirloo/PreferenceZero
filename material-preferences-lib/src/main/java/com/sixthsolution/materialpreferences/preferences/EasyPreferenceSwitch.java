package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.util.AttributeSet;

import com.sixthsolution.materialpreferences.R;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-05
 */
public class EasyPreferenceSwitch extends EasyPreferenceSwitchCompoundButton {

    public EasyPreferenceSwitch(Context context) {
        super(context);
    }

    public EasyPreferenceSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyPreferenceSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayout() {
        return R.layout.easy_preference_switch;
    }
}
