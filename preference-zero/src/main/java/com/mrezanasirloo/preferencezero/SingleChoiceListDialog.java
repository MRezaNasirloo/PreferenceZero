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

package com.mrezanasirloo.preferencezero;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author : M.Reza.Nasirloo@gmail.com Created on: 2016-01-07
 */
public class SingleChoiceListDialog extends DialogFragment<Integer> {
//    private TextView tvHeaderTitle;

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
            tittle = getArguments().getString(TITTLE);
            selectedItem = getArguments().getInt(SELECTED_ITEMs, 0);
            arrayEntries = getArguments().getCharSequenceArray(ARRAY_ENTRIES);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO: 2016-06-19 make the header optional
//        View header = inflateLayout();

//        tvHeaderTitle = (TextView) header.findViewById(R.id.header_title);
//        tvHeaderTitle.setText(tittle);

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
//                .setCustomTitle(header)
                .create();
    }

    /**
     * Child classes can over ride this and provide their own views
     *
     * @return a header
     */
    protected View inflateLayout() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.preference_list_dialog_header, ((ViewGroup) getView()), false);
    }
}