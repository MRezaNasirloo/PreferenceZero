package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.R;

/**
 * Created by mehdok on 5/16/2016.
 */
public class EasyPreferencesHeader extends EasyPreference<Void> {

    private TextView tvTitle;
    private ImageView imgIcon;

    @Override
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

        tvTitle = (TextView) findViewById(R.id.easy_group_title);
        imgIcon = (ImageView) findViewById(R.id.easy_group_icon);

        tvTitle.setText(mTittle);

        // show or hide icon
        if (mIcon < 0) {
            imgIcon.setVisibility(GONE);
        } else {
            imgIcon.setImageResource(mIcon);
        }
    }

    // This class is not interactive, so th following methods has no use
    @Override
    public void onClick(View view) {

    }

    @Override
    public void save(Void pref) {

    }

    @Override
    public Void load() {
        return null;
    }
}
