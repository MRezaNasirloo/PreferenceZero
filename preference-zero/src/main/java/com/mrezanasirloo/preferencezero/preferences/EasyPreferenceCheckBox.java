package com.mrezanasirloo.preferencezero.preferences;

import android.content.Context;
import android.util.AttributeSet;

import com.mrezanasirloo.preferencezero.R;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-05
 */
public class EasyPreferenceCheckBox extends EasyPreferenceSwitchCompoundButton {

    public EasyPreferenceCheckBox(Context context) {
        super(context);
    }

    public EasyPreferenceCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyPreferenceCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayout() {
        return R.layout.easy_preference_checkbox;
    }
}
