package com.mrezanasirloo.preferencezero.preferences.lists;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.mrezanasirloo.preferencezero.preferences.PreferenceZero;

/**
 * Created by mehdok on 6/8/2016.
 */
public abstract class PreferencesInteger extends PreferenceZero<Integer> {

    protected Integer defaultValue = 0;

    public PreferencesInteger(Context context) {
        super(context);
    }

    public PreferencesInteger(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreferencesInteger(Context context, AttributeSet attrs, int defStyleAttr) {
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
