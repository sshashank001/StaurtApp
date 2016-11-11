package com.example.chetan.staurtapp.ui.splash;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chetan.staurtapp.R;
import com.example.chetan.staurtapp.ui.home.HomeActivity;
import com.example.chetan.staurtapp.ui.login.LoginActivity;
import com.example.chetan.staurtapp.ui.model.StaurtDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import util.Constants;
import util.PreferenceHelper;

public class SplashActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressDialog = new ProgressDialog(SplashActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating");
        showProgress();
        PreferenceHelper preferenceHelper = new PreferenceHelper(SplashActivity.this);
        String guestJson = preferenceHelper.getPrefrance(Constants.KEY_PRF_STUART);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        if(guestJson != null){
            StaurtDetail mGuest = gson.fromJson(guestJson, StaurtDetail.class);
            if(mGuest != null){
                if(isNetworkConnected()==true){
                    hideProgress();
                    Intent mIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    mIntent.putExtra("STUART", mGuest);
                    startActivity(mIntent);
                    finish();

                } else {
                    showDialog();
                    hideProgress();
                }

            }else{
                throw new NullPointerException();
            }
        }else{
            hideProgress();
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this,R.style.AppTheme_AlertDialog);
        builder.setMessage("No Internet Connection");

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showProgress() {
        progressDialog.show();
    }


    public void hideProgress() {
        progressDialog.dismiss();

    }
}
