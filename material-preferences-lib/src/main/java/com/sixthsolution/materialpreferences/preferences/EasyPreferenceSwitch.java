package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.R;

/**
 * Created by mehdok on 5/16/2016.
 */
public class EasyPreferenceSwitch extends EasyPreferenceBoolean {
    private TextView mTextViewTittle;
    private String mOffText;
    private String mOnText;
    private TextView tvDetail;
    private ImageView imgIcon;

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

        compoundButton = (SwitchCompat) findViewById(R.id.easy_checkable);
        mTextViewTittle = (TextView) findViewById(R.id.easy_tittle);
        tvDetail = (TextView) findViewById(R.id.easy_detail);
        imgIcon = (ImageView) findViewById(R.id.easy_icon);

        // show or hide detailText
        if (detailText == null) {
            tvDetail.setVisibility(GONE);
        } else {
            tvDetail.setText(detailText);
        }

        // show or hide icon
        if (iconResId < 0) {
            imgIcon.setVisibility(INVISIBLE);
        } else {
            imgIcon.setImageResource(iconResId);
        }

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);

        defaultValue = typedArray.getBoolean(R.styleable.EasyPreference_ep_default, defaultValue);
        mOffText = typedArray.getString(R.styleable.EasyPreference_ep_off_text);
        mOnText = typedArray.getString(R.styleable.EasyPreference_ep_on_text);
        typedArray.recycle();

        ((SwitchCompat) compoundButton).setTextOff(mOffText);
        ((SwitchCompat) compoundButton).setTextOn(mOnText);
        ((SwitchCompat) compoundButton).setShowText(true);

        // Save the default value if is not set already.
        if (!isInEditMode()) {
            save(load());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mTextViewTittle.setText(tittle);
        compoundButton.setChecked(load());
    }

}
