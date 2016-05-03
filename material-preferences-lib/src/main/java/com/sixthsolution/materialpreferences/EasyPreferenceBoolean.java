package com.sixthsolution.materialpreferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2016-01-06
 */
public abstract class EasyPreferenceBoolean extends EasyPreference<Boolean> {
    protected CompoundButton mSwitch;
    protected boolean mDefault = true;

    public EasyPreferenceBoolean(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EasyPreferenceBoolean(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyPreferenceBoolean(Context context) {
        super(context);
    }

    protected abstract void init(Context context, AttributeSet attrs, int defStyleAttr);

    @Override
    public void onClick(View v) {
        boolean checked = !load();
        mSwitch.setChecked(checked);
        save(checked);
    }

    @Override
    public void save(Boolean pref) {
        mSharedPreferences.edit().putBoolean(mKey, pref).apply();
    }

    @Override
    public Boolean load() {
        return mSharedPreferences.getBoolean(mKey, mDefault);
    }
}
