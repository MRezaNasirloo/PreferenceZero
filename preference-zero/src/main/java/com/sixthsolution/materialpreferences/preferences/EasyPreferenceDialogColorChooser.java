package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.CircleView;
import com.sixthsolution.materialpreferences.R;
import com.sixthsolution.materialpreferences.Showable;
import com.sixthsolution.materialpreferences.preferences.lists.EasyPreferenceDialog;

import petrov.kristiyan.colorpicker.ColorPicker;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-06
 */
public class EasyPreferenceDialogColorChooser extends EasyPreferenceDialog<Integer> {

    protected int defaultValue;
    protected TextView mTextViewTittle;
    private CircleView mFAB;

    @Override
    protected int getLayout() {
        return R.layout.easy_preference_dialog_color_chooser;
    }

    public EasyPreferenceDialogColorChooser(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferenceDialogColorChooser(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferenceDialogColorChooser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        // Load attributes
        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);
        try {
            defaultValue =
                    Integer.parseInt(typedArray.getString(R.styleable.EasyPreference_ep_default));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException(
                    "Cant use this this value as integer." + e.getMessage());
        } finally {
            typedArray.recycle();
        }

        inflate(context, getLayout(), this);

        mFAB = (CircleView) findViewById(R.id.easy_color_preview);
        mTextViewTittle = (TextView) findViewById(R.id.easy_tittle);


        // Save the default value if is not set already.
        Integer load = load();
        if (!isInEditMode()) {
            save(load);
        }
        // TODO: 2016-01-05 add the generic type to load method's arg .

        mTextViewTittle.setText(tittle);
        mFAB.setColor(load);
    }

    // TODO: 2016-01-08 add a abstract method in super class to implement for updating views
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        super.onSharedPreferenceChanged(sharedPreferences, key);
        if (key != null && this.key.equals(key)) {
            Integer load = load();
            mFAB.setColor(load);
        }
    }

    @NonNull
    @Override
    protected Showable getDialog() {
        return new Showable() {
            @Override
            public void show(AppCompatActivity activity) {
                ColorPicker colorPicker = new ColorPicker(getContext());
                colorPicker.setColors(R.array.demo_colors);
                colorPicker.setTitle(tittle);
                colorPicker.setRoundColorButton(true);
                colorPicker.setDefaultColorButton(load());
                colorPicker.setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {
                    @Override
                    public void setOnFastChooseColorListener(int position, int color) {
                        save(color);
                    }
                });
                colorPicker.show();
            }
        };
    }

    @Override
    public void save(Integer pref) {
        sharedPreferences.edit().putInt(key, pref).apply();

    }

    @Override
    public Integer load() {
        return sharedPreferences.getInt(key, defaultValue);
    }
}
