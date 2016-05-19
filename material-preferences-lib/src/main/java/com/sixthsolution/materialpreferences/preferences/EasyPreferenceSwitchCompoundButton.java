package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.R;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-05
 */
public abstract class EasyPreferenceSwitchCompoundButton extends EasyPreferenceBoolean implements
        CompoundButton.OnCheckedChangeListener {

    protected TextView textViewTittle;
    protected TextView textViewDetail;
    protected ImageView imageViewIcon;

    public EasyPreferenceSwitchCompoundButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferenceSwitchCompoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferenceSwitchCompoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, getLayout(), this);

        compoundButton = (CompoundButton) findViewById(R.id.easy_checkable);
        compoundButton.setOnCheckedChangeListener(this);
        textViewTittle = (TextView) findViewById(R.id.easy_tittle);
        textViewDetail = (TextView) findViewById(R.id.easy_detail);
        imageViewIcon = (ImageView) findViewById(R.id.easy_icon);

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

    @Override
    public void onCheckedChanged(CompoundButton button, boolean value) {
        if (detailTextON != null && value) {
            textViewDetail.setVisibility(VISIBLE);
            textViewDetail.setText(detailTextON);
        } else if (detailTextOFF != null && !value) {
            textViewDetail.setVisibility(VISIBLE);
            textViewDetail.setText(detailTextOFF);
        } else {
            textViewDetail.setVisibility(GONE);
        }
    }
}
