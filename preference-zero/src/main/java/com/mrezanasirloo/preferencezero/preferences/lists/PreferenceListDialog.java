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

package com.mrezanasirloo.preferencezero.preferences.lists;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrezanasirloo.preferencezero.DialogFragment;
import com.mrezanasirloo.preferencezero.R;
import com.mrezanasirloo.preferencezero.Showable;
import com.mrezanasirloo.preferencezero.SingleChoiceListDialog;


/**
 * @author : M.Reza.Nasirloo@gmail.com Created on: 2016-01-06
 * <p>
 * It's Component to show a preferecne status and save/load the data from {@link SharedPreferences}
 */
public class PreferenceListDialog extends PreferenceDialog<Integer> {

    private static final String TAG = PreferenceListDialog.class.getSimpleName();
    protected CharSequence[] mArrayEntries;
    protected CharSequence[] mArrayValues;
    protected int mDefault;
    protected TextView mTextViewTittle;
    protected TextView mTextViewSummary;
    private TextView tvDetail;
    private ImageView imgIcon;
    private String index = "_$Index$";

    @Override
    public int getLayout() {
        return R.layout.preference_dialog;
    }

    public PreferenceListDialog(Context context) {
        super(context);
        init(context, null, 0);
    }

    public PreferenceListDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PreferenceListDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        // Load attributes
        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.PreferenceZero, defStyleAttr, 0);
        try {
            mArrayEntries = typedArray.getTextArray(R.styleable.PreferenceZero_android_entries);
            mArrayValues = typedArray.getTextArray(R.styleable.PreferenceZero_android_entryValues);
            // FIXME: 2016-08-06 find the index of default value in array values returns 0 if cannot be found
            mDefault = Integer.parseInt(typedArray.getString(R.styleable.PreferenceZero_pz_default));

        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException("Cant use this this value as integer." + e.getMessage());
        } finally {
            typedArray.recycle();
        }

        View.inflate(context, getLayout(), this);

        mTextViewSummary = (TextView) findViewById(R.id.easy_summary);
        mTextViewTittle = (TextView) findViewById(R.id.easy_tittle);
        tvDetail = (TextView) findViewById(R.id.easy_detail);
        imgIcon = (ImageView) findViewById(R.id.easy_icon);

        // show or hide detailText
        if (detailTextON == null) {
            tvDetail.setVisibility(GONE);
        } else {
            tvDetail.setVisibility(VISIBLE);
            tvDetail.setText(detailTextON);
        }

        // show or hide icon
        if (iconResId < 0) {
            imgIcon.setVisibility(GONE);
        } else {
            imgIcon.setImageResource(iconResId);
        }


        // Save the default value if is not set already.
        if (!isInEditMode()) {
            save(load());
        }
        // TODO: 2016-01-05 add the generic type to load method's arg .

        mTextViewTittle.setText(tittle);
        if (mArrayEntries == null)
            throw new IllegalArgumentException("Entity list is null, set it in preference android:entities:");
        mTextViewSummary.setText(mArrayEntries[load()]);
    }

    // TODO: 2016-01-08 add a abstract method in super class to implement for updating views
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        super.onSharedPreferenceChanged(sharedPreferences, key);
        Log.i(TAG, "onSharedPreferenceChanged() returned: " + key);
        if (key != null && key.equals(this.key)) {
            mTextViewSummary.setText(mArrayEntries[load()]);
        }
    }

    @NonNull
    @Override
    protected Showable getDialog() {
        // TODO: 2016-05-03 use your own builder or bundle ---> DONE
        return SingleChoiceListDialog.newInstance(load(), mArrayEntries, tittle)
                .setOnItemSelectedListener(new DialogFragment.OnItemSelectedListener<Integer>() {
                    @Override
                    public void OnItemSelected(Integer which) {
                        save(which);
                    }
                });
        /*SingleChoiceListDialog mListDialog = new ListDialogBuilder(mArrayEntries, key, "Select", load(), "Single list").build();
        mListDialog.show(((AppCompatActivity) getContext()), "someTag");*/
    }

    @Override
    public void save(Integer pref) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key + index, pref);
        editor.putString(key, mArrayValues[pref].toString());
        editor.apply();

    }

    @Override
    public Integer load() {
        return sharedPreferences.getInt(key + index, mDefault);
    }
}
