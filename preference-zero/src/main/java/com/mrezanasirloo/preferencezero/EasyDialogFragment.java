package com.mrezanasirloo.preferencezero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;


/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-07
 *
 * Its the base class for showing a dialog to user, it is not responsible for any save or load data
 */
public abstract class EasyDialogFragment<T> extends AppCompatDialogFragment implements Showable {


    public interface OnItemSelectedListener<I> {
        void OnItemSelected(I which);
    }

    protected static final String SELECTED_ITEMs = "selected_item";
    protected static final String ARRAY_ENTRIES = "array_entries";
    protected static final String TITTLE = "tittle";

    protected T selectedItem;
    protected String tittle;
    protected CharSequence[] arrayEntries;

    OnItemSelectedListener<T> listener;

    /**
     * Set On Item(s) selected listener
     *
     * @param listener
     */
    public EasyDialogFragment<T> setOnItemSelectedListener(OnItemSelectedListener<T> listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
    public void show(AppCompatActivity activity) {
        show(activity, getTag());
    }

    @Override
    public void onDestroyView() {
        // work around for #setRetainInstance(true) not working in DialogFragments
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }
}