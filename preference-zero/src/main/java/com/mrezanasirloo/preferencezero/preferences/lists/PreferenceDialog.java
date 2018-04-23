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

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.mrezanasirloo.preferencezero.Showable;
import com.mrezanasirloo.preferencezero.preferences.PreferenceZero;

/**
 * @author : M.Reza.Nasirloo@gmail.com Created on: 2016-01-06
 *
 * Base class for List type preferecnces
 */
public abstract class PreferenceDialog<T> extends PreferenceZero<T> {

    public PreferenceDialog(Context context) {
        super(context, null, 0);
    }

    public PreferenceDialog(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public PreferenceDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        show();
    }

    /**
     * show A dialog to show in onClick
     */
    private void show() {
        getDialog().show((AppCompatActivity) getContext());
    }

    /**
     * Implement this method and create a {@link Dialog} to show
     *
     * @return Dialog
     */
    @NonNull
    protected abstract Showable getDialog();
}
