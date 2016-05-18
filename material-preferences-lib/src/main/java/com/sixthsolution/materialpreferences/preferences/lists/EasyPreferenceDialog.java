package com.sixthsolution.materialpreferences.preferences.lists;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.sixthsolution.materialpreferences.ShowAble;
import com.sixthsolution.materialpreferences.preferences.EasyPreference;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-06
 *
 * Base class for List type preferecnces
 */
public abstract class EasyPreferenceDialog<T> extends EasyPreference<T> {

    public EasyPreferenceDialog(Context context) {
        super(context, null, 0);
    }

    public EasyPreferenceDialog(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public EasyPreferenceDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        show();
    }

    /**
     * show A dialog to show in onClick
     */
    protected void show() {
        getDialog().show((AppCompatActivity) getContext());
    }

    /**
     * Implement this method and create a {@link Dialog} to show
     *
     * @return Dialog
     */
    protected abstract ShowAble getDialog();
}
