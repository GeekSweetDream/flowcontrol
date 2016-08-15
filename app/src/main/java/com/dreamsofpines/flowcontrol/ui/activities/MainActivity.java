package com.dreamsofpines.flowcontrol.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.storage.preferenses.GlobalPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context AppContext;
    private TextView enterBtn;
    private TextInputLayout inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppContext = getApplicationContext();
//        mySetting = getSharedPreferences(GlobalPreferences.getMySettings(), Context.MODE_PRIVATE);
        inputPassword = (TextInputLayout) findViewById(R.id.passwordWrapper);
        enterBtn = (TextView) findViewById(R.id.buttonEnter);
        enterBtn.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("password", String.valueOf(inputPassword.getText()))
    }

    @Override
    public void onClick(View v) {

        if(GlobalPreferences.getMyPASSWORD(AppContext) != null){
            String password = GlobalPreferences.getMyPASSWORD(AppContext);
            if(password.compareTo(inputPassword.getEditText().getText().toString()) == 0){
                inputPassword.setErrorEnabled(false);
                Intent intent = new Intent(MainActivity.this,HomePages.class);
                startActivity(intent);
            } else {
                inputPassword.setError("Wrong password");
            }
        }
    }
}
