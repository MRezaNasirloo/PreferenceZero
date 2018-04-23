package com.mrezanasirloo.preferencezero.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mrezanasirloo.preferencezero.R;
import com.mrezanasirloo.preferencezero.preferences.lists.EasyPreferencesInteger;


/**
 * Created by mehdok on 6/8/2016.
 */
public class EasyPreferencesSeekBar extends EasyPreferencesInteger implements
        SeekBar.OnSeekBarChangeListener {

    private int minValue;
    private int maxValue;
    private boolean showValue;
    private TextView tvValue;
    private AppCompatSeekBar seekBar;
    private TextView textViewTittle;
    private TextView textViewDetail;
    private ImageView imageViewIcon;

    public EasyPreferencesSeekBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferencesSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferencesSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayout() {
        return R.layout.easy_preferences_seekbar;
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, getLayout(), this);

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);

        defaultValue = typedArray.getInt(R.styleable.EasyPreference_ep_default, defaultValue);
        minValue = typedArray.getInt(R.styleable.EasyPreference_ep_min_value, 0);
        maxValue = typedArray.getInt(R.styleable.EasyPreference_ep_max_value, 10);
        if (defaultValue < minValue || defaultValue > maxValue)
            defaultValue = minValue;

        showValue = typedArray.getBoolean(R.styleable.EasyPreference_ep_show_value, true);
        typedArray.recycle();

        tvValue = (TextView) findViewById(R.id.easy_seekbar_value);
        seekBar = (AppCompatSeekBar) findViewById(R.id.easy_seekbar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(maxValue - minValue);
        textViewTittle = (TextView) findViewById(R.id.easy_tittle);
        textViewDetail = (TextView) findViewById(R.id.easy_detail);
        imageViewIcon = (ImageView) findViewById(R.id.easy_icon);

        // show or hide icon
        if (iconResId < 0) {
            imageViewIcon.setVisibility(GONE);
        } else {
            imageViewIcon.setImageResource(iconResId);
        }

        // show or hide detail
        if (detailTextON != null) {
            textViewDetail.setVisibility(VISIBLE);
            textViewDetail.setText(detailTextON);
        } else {
            textViewDetail.setVisibility(GONE);
        }

        if (showValue) {
            tvValue.setVisibility(VISIBLE);
            tvValue.setText(String.valueOf(load()));
        } else {
            tvValue.setVisibility(GONE);
        }

        seekBar.setProgress(load() - minValue);

        // Save the default value if is not set already.
        if (!isInEditMode()) {
            save(load());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        textViewTittle.setText(tittle);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        tvValue.setText(String.valueOf(i + minValue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        save(seekBar.getProgress() + minValue);
    }
}
