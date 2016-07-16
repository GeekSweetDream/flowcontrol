package com.dreamsofpines.flowcontrol.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamsofpines.flowcontrol.R;

public class MainActivity extends AppCompatActivity {

    private TextView enterBtn;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputPassword = (EditText) findViewById(R.id.editLogin);
        enterBtn = (TextView) findViewById(R.id.buttonEnter);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HomePages.class);
                startActivity(intent);
            }
        });
//        if(null != savedInstanceState){
//            inputPassword.setText(savedInstanceState.getString("password"));
//        }
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
}
