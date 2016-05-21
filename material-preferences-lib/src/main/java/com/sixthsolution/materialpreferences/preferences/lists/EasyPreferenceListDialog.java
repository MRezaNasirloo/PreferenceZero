package com.sixthsolution.materialpreferences.preferences.lists;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixthsolution.materialpreferences.EasyDialogFragment;
import com.sixthsolution.materialpreferences.R;
import com.sixthsolution.materialpreferences.ShowAble;
import com.sixthsolution.materialpreferences.SingleChoiceListDialog;


/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-06
 *
 * It's Component to show a preferecne status and save/load the data from {@link SharedPreferences}
 */
public class EasyPreferenceListDialog extends EasyPreferenceDialog<Integer> {

    private static final String TAG = EasyPreferenceListDialog.class.getSimpleName();
    protected CharSequence[] mArrayEntries;
    protected CharSequence[] mArrayValues;
    protected int mDefault;
    protected TextView mTextViewTittle;
    protected TextView mTextViewSummary;
    private TextView tvDetail;
    private ImageView imgIcon;

    @Override
    public int getLayout() {
        return R.layout.easy_preference_dialog;
    }

    public EasyPreferenceListDialog(Context context) {
        super(context);
        init(context, null, 0);
    }

    public EasyPreferenceListDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EasyPreferenceListDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        // Load attributes
        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);
        try {
            mArrayEntries = typedArray.getTextArray(R.styleable.EasyPreference_android_entries);
            mArrayValues = typedArray.getTextArray(R.styleable.EasyPreference_android_entryValues);
            mDefault = Integer.parseInt(typedArray.getString(R.styleable.EasyPreference_ep_default));
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
            tvDetail.setText(detailTextON);
        }

        // show or hide icon
        if (iconResId < 0) {
            imgIcon.setVisibility(INVISIBLE);
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

    @Override
    protected ShowAble getDialog() {
        // TODO: 2016-05-03 use your own builder or bundle ---> DONE
        return SingleChoiceListDialog.newInstance(load(), mArrayEntries, tittle)
                .setOnItemSelectedListener(new EasyDialogFragment.OnItemSelectedListener<Integer>() {
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
        sharedPreferences.edit().putInt(key, pref).apply();

    }

    @Override
    public Integer load() {
        return sharedPreferences.getInt(key, mDefault);
    }
}
