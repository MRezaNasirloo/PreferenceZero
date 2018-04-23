package com.mrezanasirloo.preferencezero.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mrezanasirloo.preferencezero.PersistPref;
import com.mrezanasirloo.preferencezero.R;

/**
 * @author : M.Reza.Nasirloo@gmail.com Created on: 2016-01-05
 *         <p>
 *         A replacement for android {@link Preference} class. This the base class for
 *         all Preferences inside this package.
 */
public abstract class PreferenceZero<T> extends RelativeLayout
        implements SharedPreferences.OnSharedPreferenceChangeListener, View.OnClickListener,
        PersistPref<T> {


    private static final String STATE = "_$state$";
    protected String key;
    protected String dependency;
    protected String tittle;
    protected String summary;
    protected String detailTextON;
    protected String detailTextOFF;
    protected int iconResId;
    protected SharedPreferences sharedPreferences;

    protected abstract int getLayout();

    public PreferenceZero(Context context) {
        super(context, null, R.attr.pz_style);
        init(context, null, 0);

    }

    public PreferenceZero(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.pz_style);
        init(context, attrs, 0);
    }

    public PreferenceZero(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.PreferenceZero, defStyleAttr, 0);

        key = typedArray.getString(R.styleable.PreferenceZero_pz_key);
        if (key == null) {
            throw new RuntimeException("You must set pz_key, it is required");
        }

        tittle = typedArray.getString(R.styleable.PreferenceZero_pz_title);
        summary = typedArray.getString(R.styleable.PreferenceZero_pz_summary);
        dependency = typedArray.getString(R.styleable.PreferenceZero_pz_dependency);
        detailTextON = typedArray.getString(R.styleable.PreferenceZero_pz_detail_on);
        detailTextOFF = typedArray.getString(R.styleable.PreferenceZero_pz_detail_off);
        iconResId = typedArray.getResourceId(R.styleable.PreferenceZero_pz_icon, -1);

        typedArray.recycle();

        // Enable selectable background
        int[] androidAttrs = new int[] {R.attr.selectableItemBackground};
        final TypedArray typedArrayBackground = context.obtainStyledAttributes(androidAttrs);
        int backgroundResource = typedArrayBackground.getResourceId(0, 0);
        setBackgroundResource(backgroundResource);
        typedArrayBackground.recycle();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // if the key is this preference dependency disable/enable child views.
        if (key.equals(dependency)) {
            // TODO: 2016-01-08 Adds cast exception if the dependency is not boolean
            boolean Dependency = this.sharedPreferences.getBoolean(key, false);
            boolean DependencyState = this.sharedPreferences.getBoolean(key + STATE, false);
            boolean aBoolean = Dependency && DependencyState;
            setChildViewsEnable(this, aBoolean);
        }
        if (key.equals(dependency + STATE)) {
            // TODO: 2016-01-08 Adds cast exception if the dependency is not boolean
            boolean Dependency = this.sharedPreferences.getBoolean(dependency, false);
            boolean DependencyState = this.sharedPreferences.getBoolean(key, false);
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
            if (childAt instanceof ViewGroup) {
                setChildViewsEnable(((ViewGroup) childAt), enable);
            }
            childAt.setEnabled(enable);
        }

        // Change the clickable state accordingly
        setClickable(enable);
        if (!isInEditMode()) {
            sharedPreferences.edit().putBoolean(key + STATE, enable).apply();
        }

    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        setOnClickListener(this);

        // So if we have a dependency load it's state and if it was false disable this viewGroup's childViews.
        if (dependency != null) {
            boolean dependency = sharedPreferences.getBoolean(this.dependency, false);
            boolean dependencyState = sharedPreferences.getBoolean(this.dependency + STATE, false);
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
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        setOnClickListener(null);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                getResources().getDimensionPixelSize(R.dimen.layout_max_height),
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }*/
}
