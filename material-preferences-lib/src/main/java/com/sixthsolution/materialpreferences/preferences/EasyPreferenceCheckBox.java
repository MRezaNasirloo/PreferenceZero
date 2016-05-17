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
public class EasyPreferenceCheckBox extends EasyPreferenceBoolean {

    private TextView mTextViewTittle;
    private TextView tvDetail;
    private ImageView imgIcon;

    @Override
    public int getLayout() {
        return R.layout.easy_preference_checkbox;
    }

    public EasyPreferenceCheckBox(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferenceCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferenceCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, getLayout(), this);

        mSwitch = (CompoundButton) findViewById(R.id.easy_checkbox);
        mTextViewTittle = (TextView) findViewById(R.id.easy_tittle);
        tvDetail = (TextView) findViewById(R.id.easy_detail);
        imgIcon = (ImageView) findViewById(R.id.easy_icon);

        // show or hide detail
        if (mDetail == null) {
            tvDetail.setVisibility(GONE);
        } else {
            tvDetail.setText(mDetail);
        }

        // show or hide icon
        if (mIcon < 0) {
            imgIcon.setVisibility(INVISIBLE);
        } else {
            imgIcon.setImageResource(mIcon);
        }

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);

        try {
            mDefault = typedArray.getBoolean(R.styleable.EasyPreference_ep_default, mDefault);
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException("The default value cannot cast to Boolean. " + e.getMessage());
        }
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
