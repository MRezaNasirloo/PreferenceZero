package com.sixthsolution.materialpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;


/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-07
 */
public abstract class EasyDialogFragment<T> extends AppCompatDialogFragment implements PersistPref<T> {

    protected SharedPreferences mSharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    /**
     * @param activity Container activity
     * @param tag      the tag for fragment
     * @return this fragment
     */
    public AppCompatDialogFragment show(AppCompatActivity activity, String tag) {
        show(activity.getSupportFragmentManager(), tag);
        return this;
    }

    @Override
    public void onDestroyView() {
        // work around for #setRetainInstance(true) not working in DialogFragments
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }
}