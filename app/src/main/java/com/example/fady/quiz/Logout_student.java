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
 * Created by fady on 2/13/2015.
 */
public class Logout_student extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.alert)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RestAdapter adapter=new RestAdapter.Builder()
                                .setLogLevel(RestAdapter.LogLevel.FULL)
                                .setEndpoint(Login.ENDPOINT)
                                .build();
                        RequestAPI api2=adapter.create(RequestAPI.class);
                        api2.delete(Login.Token,new Callback<Delete>() {
                            @Override
                            public void success(Delete delete, Response response) {

                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                        Intent in1 = new Intent(getActivity(),Login.class);
                        startActivity(in1);

                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
