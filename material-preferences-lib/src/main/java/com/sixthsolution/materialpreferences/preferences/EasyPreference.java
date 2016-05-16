package com.sixthsolution.materialpreferences.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sixthsolution.materialpreferences.PersistPref;
import com.sixthsolution.materialpreferences.R;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-05
 *         <p/>
 *         A replacement for android {@link Preference} class. This the base class for
 *         EasyPreference.
 */
public abstract class EasyPreference<T> extends LinearLayout
        implements SharedPreferences.OnSharedPreferenceChangeListener, View.OnClickListener, PersistPref<T> {


    private static final String STATE = "_$state$";
    protected T mType;
    protected String mKey;
    protected String mDependency;
    protected String mTittle;
    protected String mSummary;
    protected int mLayoutResId;
    protected SharedPreferences mSharedPreferences;

    public abstract int getLayout();

    public EasyPreference(Context context) {
        super(context, null, R.attr.ep_style);
        init(context, null, 0);

    }

    public EasyPreference(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.ep_style);
        init(context, attrs, 0);
    }

    public EasyPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EasyPreference, defStyleAttr, 0);

        mKey = typedArray.getString(R.styleable.EasyPreference_ep_key);
        if (mKey == null) {
            throw new RuntimeException("You must set ep_key, it is required");
        }

        mTittle = typedArray.getString(R.styleable.EasyPreference_ep_title);
        mSummary = typedArray.getString(R.styleable.EasyPreference_ep_summary);
        mDependency = typedArray.getString(R.styleable.EasyPreference_ep_dependency);

        typedArray.recycle();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // if the key is this preference dependency disable/enable child views.
        if (key.equals(mDependency)) {
            // TODO: 2016-01-08 Adds cast exception if the dependency is not boolean
            boolean Dependency = mSharedPreferences.getBoolean(key, false);
            boolean DependencyState = mSharedPreferences.getBoolean(key + STATE, false);
            boolean aBoolean = Dependency && DependencyState;
            setChildViewsEnable(this, aBoolean);
        }
        if (key.equals(mDependency + STATE)) {
            // TODO: 2016-01-08 Adds cast exception if the dependency is not boolean
            boolean Dependency = mSharedPreferences.getBoolean(mDependency, false);
            boolean DependencyState = mSharedPreferences.getBoolean(key, false);
            boolean aBoolean = Dependency && DependencyState;
            setChildViewsEnable(this, aBoolean);
        }
    }

    /**
     * Looks for childViews recursively and set their state to enable param.
     *
     * @param viewGroup the viewGroup to disable/enable its child
     * @param enable    the state of childViews
     */
    private void setChildViewsEnable(ViewGroup viewGroup, boolean enable) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup)
                setChildViewsEnable(((ViewGroup) childAt), enable);
            childAt.setEnabled(enable);
        }

        // Change the clickable state accordingly
        setClickable(enable);
        if (!isInEditMode())
            mSharedPreferences.edit().putBoolean(mKey + STATE, enable).apply();

    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        setOnClickListener(this);

        // So if we have a dependency load it's state and if it was false disable this viewGroup's childViews.
        if (mDependency != null) {
            boolean dependency = mSharedPreferences.getBoolean(mDependency, false);
            boolean dependencyState = mSharedPreferences.getBoolean(mDependency + STATE, false);
            boolean aBoolean = dependency && dependencyState;
            setChildViewsEnable(this, aBoolean);
        } else {
            // if this preference dose not have dependency set its child views and its state enable.
            setChildViewsEnable(this, true);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        setOnClickListener(null);
    }
}
