package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.R;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2016-01-05
 */
public class EasyPreferenceCompoundSwitch extends EasyPreferenceBoolean {

    private TextView mTextViewTittle;

    @Override
    public int getLayout() {
        return R.layout.easy_preference_switch;
    }

    public EasyPreferenceCompoundSwitch(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferenceCompoundSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferenceCompoundSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, getLayout(), this);

        mSwitch = (CompoundButton) findViewById(R.id.easy_switch);
        mTextViewTittle = (TextView) findViewById(R.id.easy_tittle);

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);

        mDefault = typedArray.getBoolean(R.styleable.EasyPreference_ep_default, mDefault);
        typedArray.recycle();

        // Save the default value if is not set already.
        if (!isInEditMode()) {
            save(load());
        }
        // TODO: 2016-01-05 add the generic type to load method's arg .

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mTextViewTittle.setText(mTittle);
        mSwitch.setChecked(load());
    }
}