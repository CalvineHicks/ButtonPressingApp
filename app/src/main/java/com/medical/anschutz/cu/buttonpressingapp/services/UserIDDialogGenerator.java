package com.medical.anschutz.cu.buttonpressingapp.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.medical.anschutz.cu.buttonpressingapp.R;


public class UserIDDialogGenerator {
    public UserIDDialogGenerator() {}

    public EditText inputValue;

    public void showGetUserIDDialog(Context context
            , String title
            , String positiveBtnTxt
            , String negativeBtnTxt
            , DialogInterface.OnClickListener positiveBtnListener
            , DialogInterface.OnClickListener negativeBtnListener){

        final AlertDialog sessionDialog;
        AlertDialog.Builder sessionDialogBuilder = new AlertDialog.Builder(context);
        // Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.get_user_id_modal, null);
        view.findViewById(R.id.textField);
        inputValue = view.findViewById(R.id.textField);
        sessionDialogBuilder.setView(view);
        sessionDialogBuilder.setTitle(title);
        sessionDialogBuilder.setPositiveButton(positiveBtnTxt, positiveBtnListener);
        sessionDialogBuilder.setNegativeButton(negativeBtnTxt, negativeBtnListener);
        sessionDialog = sessionDialogBuilder.create();
        sessionDialog.show();
    }

}
