package com.example.fady.quiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by fady on 2/14/2015.
 */
public class ShowGrade extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your Grade is "+quiz.mark.getGrade()+"%")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        quiz.array_correct_answer_save.clear();

                        Intent in1 = new Intent(getActivity(),student.class);
                        startActivity(in1);

                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}

