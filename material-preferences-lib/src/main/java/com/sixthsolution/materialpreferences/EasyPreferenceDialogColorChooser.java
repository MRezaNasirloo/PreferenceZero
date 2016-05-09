package com.sixthsolution.materialpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

//import com.afollestad.materialdialogs.color.ColorChooserDialog;
//import com.orhanobut.logger.Logger;
//import com.sixthsolution.tosanbanking.app.ui.main.activity.SettingsActivity;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-06
 */
// TODO: 2016-05-03 extracts this to it's own modules as it depends on Martial Dialog library
public class EasyPreferenceDialogColorChooser {
/* extends EasyPreferenceDialog<Integer> {

    protected int mDefault;
    protected TextView mTextViewTittle;
    private CircleView mFAB;

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

            mDefault = Integer.parseInt(typedArray.getString(R.styleable.EasyPreference_ep_default));


        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException("Cant use this this value as integer." + e.getMessage());
        } finally {
            typedArray.recycle();
        }

        inflate(context, R.layout.easy_preference_dialog_color_chooser, this);

        mFAB = (CircleView) findViewById(R.id.easy_color_preview);
        mTextViewTittle = (TextView) findViewById(R.id.easy_tittle);


        // Save the default value if is not set already.
        Integer load = load();
        if (!isInEditMode()) {
            save(load);
        }
        // TODO: 2016-01-05 add the generic type to load method's arg .

        mTextViewTittle.setText(mTittle);
        mFAB.setColor(load);
    }

    // TODO: 2016-01-08 add a abstract method in super class to implement for updating views
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        super.onSharedPreferenceChanged(sharedPreferences, key);
        Logger.t("onSharedPreference").d("onSharedPreferenceChanged() returned: " + key);
        if (key != null && key.equals(mKey)) {
            Integer load = load();
            mFAB.setColor(load);
        }
    }

    @Override
    public void onClick(View v) {
        show();
    }

    @Override
    protected void show() {
        new ColorChooserDialog.Builder(((SettingsActivity) getContext()), R.string.color_picker)
                .titleSub(R.string.color_picker_level)
                .presetsButton(R.string.color_picker_back)
                .cancelButton(R.string.color_picker_back)
                .doneButton(R.string.color_picker_select)
                .backButton(R.string.color_picker_back)
                .customButton(R.string.color_picker_custom)
                .allowUserColorInput(false)
                .show();
    }

    @Override
    public void save(Integer pref) {
        mSharedPreferences.edit().putInt(mKey, pref).apply();

    }

    @Override
    public Integer load() {
        return mSharedPreferences.getInt(mKey, mDefault);
    }*/
}
