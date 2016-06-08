package com.sixthsolution.materialpreferences.preferences.lists;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sixthsolution.materialpreferences.preferences.EasyPreference;

/**
 * Created by mehdok on 6/8/2016.
 */
public abstract class EasyPreferencesInteger extends EasyPreference<Integer> {

    protected Integer defaultValue = 0;

    public EasyPreferencesInteger(Context context) {
        super(context);
    }

    public EasyPreferencesInteger(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyPreferencesInteger(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected abstract void init(Context context, AttributeSet attrs, int defStyleAttr);

    @Override
    public void onClick(View view) {
        // this is not usable for seekbar
    }

    @Override
    public void save(Integer pref) {
        sharedPreferences.edit().putInt(key, pref).apply();
    }

    @Override
    public Integer load() {
        return sharedPreferences.getInt(key, defaultValue);
    }
}
