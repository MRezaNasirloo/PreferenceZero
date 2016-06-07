package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.R;

/**
 * Created by mehdok on 5/16/2016.
 */
public class EasyPreferencesHeader extends CardView {

    private TextView tvTitle;
    private ImageView imgIcon;
    private LinearLayout rootLayout;

    protected String tittle;
    protected int iconResId;

    public int getLayout() {
        return R.layout.easy_preferences_group_header;
    }

    public EasyPreferencesHeader(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferencesHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferencesHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, getLayout(), this);

        rootLayout = (LinearLayout) findViewById(R.id.category_root_layout);
        tvTitle = (TextView) findViewById(R.id.easy_group_title);
        imgIcon = (ImageView) findViewById(R.id.easy_group_icon);

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);
        tittle = typedArray.getString(R.styleable.EasyPreference_ep_title);
        iconResId = typedArray.getResourceId(R.styleable.EasyPreference_ep_icon, -1);

        tvTitle.setText(tittle);

        // show or hide icon
        if (iconResId < 0) {
            imgIcon.setVisibility(GONE);
        } else {
            imgIcon.setImageResource(iconResId);
        }

        typedArray.recycle();
    }

    @Override
    public void addView(View child) {
        if (rootLayout != null)
            rootLayout.addView(child);
        else
            super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (rootLayout != null)
            rootLayout.addView(child, index);
        else
            super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        if (rootLayout != null)
            rootLayout.addView(child, width, height);
        else
            super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (rootLayout != null)
            rootLayout.addView(child, params);
        else
            super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (rootLayout != null)
            rootLayout.addView(child, index, params);
        else
            super.addView(child, index, params);
    }
}
