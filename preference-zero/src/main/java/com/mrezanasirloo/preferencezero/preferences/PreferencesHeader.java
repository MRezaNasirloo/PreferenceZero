/*
 * MIT License
 *
 * Copyright (c) 2018 M. Reza Nasirloo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.mrezanasirloo.preferencezero.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrezanasirloo.preferencezero.R;


/**
 * Created by mehdok on 5/16/2016.
 */
public class PreferencesHeader extends CardView {

    private TextView tvTitle;
    private ImageView imgIcon;
    private LinearLayout rootLayout;

    protected String tittle;
    protected int iconResId;

    public int getLayout() {
        return R.layout.preferences_group_header;
    }

    public PreferencesHeader(Context context) {
        super(context);
        init(context, null, 0);
    }

    public PreferencesHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PreferencesHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, getLayout(), this);
        setRadius(0);

        rootLayout = (LinearLayout) findViewById(R.id.category_root_layout);
        tvTitle = (TextView) findViewById(R.id.easy_group_title);
        imgIcon = (ImageView) findViewById(R.id.easy_group_icon);

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.PreferenceZero, defStyleAttr, 0);
        tittle = typedArray.getString(R.styleable.PreferenceZero_pz_title);
        iconResId = typedArray.getResourceId(R.styleable.PreferenceZero_pz_icon, -1);

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
