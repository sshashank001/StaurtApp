package com.example.chetan.staurtapp.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chetan.staurtapp.R;
import com.example.chetan.staurtapp.ui.home.HomeActivity;
import com.example.chetan.staurtapp.ui.model.StaurtAppWebService;
import com.example.chetan.staurtapp.ui.model.StaurtDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chetan on 11/4/2016.
 */
public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    private  static final String TAG="LoginActivity";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.progress_dialog));

    }
    @OnClick(R.id.btn_login)
    public void login(){
        progressDialog.show();
        String password=etPassword.getText().toString();
        Log.d(TAG,password);
        String userName=etUserName.getText().toString();
        StaurtAppWebService staurtAppWebService=StaurtAppWebService.getInstance(this);
        staurtAppWebService.AuthenticateUser(password, userName, LoginActivity.this,new StaurtAppWebService.LoginCallBack() {
            @Override
            public void onSuccess(StaurtDetail user) {
                progressDialog.dismiss();
                Intent loginIntent=new Intent(LoginActivity.this, HomeActivity.class);
                loginIntent.putExtra("STUART", user);
                startActivity(loginIntent);
            }

            @Override
            public void onFail(String msg) {
               progressDialog.dismiss();
               Log.d(TAG,msg);
            }
        });
    }
}
