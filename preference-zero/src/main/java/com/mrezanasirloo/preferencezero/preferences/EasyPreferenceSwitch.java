package com.mrezanasirloo.preferencezero.preferences;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.mrezanasirloo.preferencezero.R;

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

    public void setOnText(String onText) {
        ((SwitchCompat) compoundButton).setTextOn(onText);
        ((SwitchCompat) compoundButton).setShowText(true);
    }

    public void setOffText(String offText) {
        ((SwitchCompat) compoundButton).setTextOff(offText);
        ((SwitchCompat) compoundButton).setShowText(true);
    }
}
