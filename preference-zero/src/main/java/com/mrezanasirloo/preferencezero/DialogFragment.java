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

package com.mrezanasirloo.preferencezero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;


/**
 * @author : M.Reza.Nasirloo@gmail.com Created on: 2016-01-07
 *
 * Its the base class for showing a dialog to user, it is not responsible for any save or load data
 */
public abstract class DialogFragment<T> extends AppCompatDialogFragment implements Showable {


    public interface OnItemSelectedListener<I> {
        void OnItemSelected(I which);
    }

    protected static final String SELECTED_ITEMs = "selected_item";
    protected static final String ARRAY_ENTRIES = "array_entries";
    protected static final String TITTLE = "tittle";

    protected T selectedItem;
    protected String tittle;
    protected CharSequence[] arrayEntries;

    OnItemSelectedListener<T> listener;

    /**
     * Set On Item(s) selected listener
     *
     * @param listener
     */
    public DialogFragment<T> setOnItemSelectedListener(OnItemSelectedListener<T> listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * @param activity Container activity
     * @param tag      the tag for fragment
     * @return this fragment
     */
    public AppCompatDialogFragment show(AppCompatActivity activity, String tag) {
        show(activity.getSupportFragmentManager(), tag);
        return this;
    }

    @Override
    public void show(AppCompatActivity activity) {
        show(activity, getTag());
    }

    @Override
    public void onDestroyView() {
        // work around for #setRetainInstance(true) not working in DialogFragments
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }
}