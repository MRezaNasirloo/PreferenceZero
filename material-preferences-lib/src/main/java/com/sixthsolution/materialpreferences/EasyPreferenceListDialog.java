package com.sixthsolution.materialpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


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

        inflate(context, R.layout.easy_preference_dialog, this);

        mTextViewSummary = (TextView) findViewById(R.id.easy_summary);
        mTextViewTittle = (TextView) findViewById(R.id.easy_tittle);


        // Save the default value if is not set already.
        if (!isInEditMode()) {
            save(load());
        }
        // TODO: 2016-01-05 add the generic type to load method's arg .

        mTextViewTittle.setText(mTittle);
        if (mArrayEntries == null)
            throw new IllegalArgumentException("Entity list is null, set it in preference android:entities:");
        mTextViewSummary.setText(mArrayEntries[load()]);
    }

    // TODO: 2016-01-08 add a abstract method in super class to implement for updating views
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        super.onSharedPreferenceChanged(sharedPreferences, key);
        Log.i(TAG, "onSharedPreferenceChanged() returned: " + key);
        if (key != null && key.equals(mKey)) {
            mTextViewSummary.setText(mArrayEntries[load()]);
        }
    }

    @Override
    protected Showable getDialog() {
        // TODO: 2016-05-03 use your own builder or bundle ---> DONE
        return SingleChoiceListDialog.newInstance(load(), mArrayEntries, "Single List")
                .setOnItemSelectedListener(new EasyDialogFragment.OnItemSelectedListener<Integer>() {
                    @Override
                    public void OnItemSelected(Integer which) {
                        save(which);
                    }
                });
        /*SingleChoiceListDialog mListDialog = new ListDialogBuilder(mArrayEntries, mKey, "Select", load(), "Single list").build();
        mListDialog.show(((AppCompatActivity) getContext()), "someTag");*/
    }

    @Override
    public void save(Integer pref) {
        mSharedPreferences.edit().putInt(mKey, pref).apply();

    }

    @Override
    public Integer load() {
        return mSharedPreferences.getInt(mKey, mDefault);
    }
}
