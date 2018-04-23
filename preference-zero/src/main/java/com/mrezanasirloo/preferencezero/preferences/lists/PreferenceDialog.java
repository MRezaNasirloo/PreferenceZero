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
 * @author : Pedramrn@gmail.com Created on: 2016-01-06
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
