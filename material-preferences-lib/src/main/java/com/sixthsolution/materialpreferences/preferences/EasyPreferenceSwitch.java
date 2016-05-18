package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.R;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2016-01-05
 */
public class EasyPreferenceSwitch extends EasyPreferenceBoolean {

    protected TextView textViewTittle;
    protected TextView textViewDetail;
    protected ImageView imageViewIcon;

    @Override
    public int getLayout() {
        return R.layout.easy_preference_switch;
    }

    public EasyPreferenceSwitch(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferenceSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferenceSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, getLayout(), this);

        compoundButton = (CompoundButton) findViewById(R.id.easy_checkable);
        textViewTittle = (TextView) findViewById(R.id.easy_tittle);
        textViewDetail = (TextView) findViewById(R.id.easy_detail);
        imageViewIcon = (ImageView) findViewById(R.id.easy_icon);

        // show or hide detailText
        if (detailTextON == null) {
            textViewDetail.setVisibility(GONE);
        } else {
            textViewDetail.setText(detailTextON);
        }

        // show or hide icon
        if (iconResId < 0) {
            imageViewIcon.setVisibility(INVISIBLE);
        } else {
            imageViewIcon.setImageResource(iconResId);
        }

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);

        defaultValue = typedArray.getBoolean(R.styleable.EasyPreference_ep_default, defaultValue);
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
        textViewTittle.setText(tittle);
        compoundButton.setChecked(load());
    }

    public void setOnText(String onText) {
        ((SwitchCompat) compoundButton).setTextOn(onText);
        ((SwitchCompat) compoundButton).setShowText(true);
    }

    public void setOffText(String offText) {
        ((SwitchCompat) compoundButton).setTextOff(offText);
        ((SwitchCompat) compoundButton).setShowText(true);
    }
}
