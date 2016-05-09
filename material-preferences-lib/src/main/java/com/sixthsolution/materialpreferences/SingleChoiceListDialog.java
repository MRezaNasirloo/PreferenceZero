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

//import com.hannesdorfmann.fragmentargs.annotation.Arg;
//import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

/**
 * @author : Pedramrn@gmail.com Created on: 2016-01-07
 */
public class SingleChoiceListDialog extends EasyDialogFragment<Integer> {

    public SingleChoiceListDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided
     * parameters.
     *
     * @return A new instance of fragment SingleChoiceListDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleChoiceListDialog newInstance(int selectedItem, CharSequence[] arrayEntries, String tittle) {
        SingleChoiceListDialog fragment = new SingleChoiceListDialog();
        Bundle args = new Bundle();
        args.putInt(SELECTED_ITEMs, selectedItem);
        args.putCharSequenceArray(ARRAY_ENTRIES, arrayEntries);
        args.putString(TITTLE, tittle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tittle = getArguments().getString(tittle);
            selectedItem = getArguments().getInt(SELECTED_ITEMs, 0);
            arrayEntries = getArguments().getCharSequenceArray(ARRAY_ENTRIES);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View header = inflateLayout();
        return new AlertDialog.Builder(getContext())
                .setSingleChoiceItems(arrayEntries,
                        selectedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (listener != null)
                                    listener.OnItemSelected(which);
                                dismiss();
                            }
                        })
                .setCustomTitle(header)
                .create();
    }

    /**
     * Child classes can over ride this and provide their own views
     *
     * @return a header
     */
    protected View inflateLayout() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.easy_preference_list_dialog_header, ((ViewGroup) getView()), false);
    }
}