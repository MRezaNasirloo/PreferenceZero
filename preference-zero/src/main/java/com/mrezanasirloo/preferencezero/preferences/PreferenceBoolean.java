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

package com.mrezanasirloo.preferencezero.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2016-01-06
 */
public abstract class PreferenceBoolean extends PreferenceZero<Boolean> {
    protected CompoundButton compoundButton;
    protected boolean defaultValue = true;

    public PreferenceBoolean(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PreferenceBoolean(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreferenceBoolean(Context context) {
        super(context);
    }

    protected abstract void init(Context context, AttributeSet attrs, int defStyleAttr);

    @Override
    public void onClick(View v) {
        boolean checked = !load();
        compoundButton.setChecked(checked);
        save(checked);
    }

    @Override
    public void save(Boolean pref) {
        sharedPreferences.edit().putBoolean(key, pref).apply();
    }

    @Override
    public Boolean load() {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
