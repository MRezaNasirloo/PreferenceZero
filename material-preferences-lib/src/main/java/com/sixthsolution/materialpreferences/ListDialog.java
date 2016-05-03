package com.sixthsolution.materialpreferences;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-07
 */
// TODO: 2016-05-03 Remove fragments args and use bundle
@FragmentWithArgs
public class ListDialog extends EasyDialogFragment<Integer> {

    @Arg
    Integer mSelected;
    @Arg
    String mTittle;
    @Arg
    String mPositive;
    @Arg
    CharSequence[] mArrayEntries;
    @Arg
    String mKey;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = inflater.inflate(R.layout.easy_preference_list_dialog_header, ((ViewGroup) getView()), false);
        return new AlertDialog.Builder(getContext(), R.style.SettingsDialog)
                .setSingleChoiceItems(mArrayEntries,
                        mSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                save(which);
                                dismiss();
                            }
                        })
                .setCustomTitle(header)
                .create();
    }

    @Override
    public void save(Integer pref) {
        mSharedPreferences.edit().putInt(mKey, pref).apply();
    }

    @Override
    public Integer load() {
        return null;
    }
}