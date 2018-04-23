/*
 * MIT License
 *
 * Copyright (c) 2018 M. Reza Nasirloo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
