package com.sixthsolution.materialpreferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-06
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
    protected abstract void show();
}
