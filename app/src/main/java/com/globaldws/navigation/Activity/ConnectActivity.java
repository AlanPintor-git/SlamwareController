package com.globaldws.navigation.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.globaldws.navigation.Classes.DataPreference;
import com.globaldws.navigation.R;

public class ConnectActivity extends AppCompatActivity {

    EditText txtip;
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        final DataPreference dataPreference = new DataPreference(getApplicationContext());

        txtip = findViewById(R.id.txtip);
        btnConnect = findViewById(R.id.btnconnect);

        String digits = "0123456789.";
        txtip.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtip.setKeyListener(DigitsKeyListener.getInstance(digits));

        if(!dataPreference.GetStoredPrefrence("ipServerAddress").equals("N/A")){
            txtip.setText(dataPreference.GetStoredPrefrence("ipServerAddress"));
        }

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtip.length() == 0 || txtip.length() == 0){
                    Toast.makeText(ConnectActivity.this, "Error ip address", Toast.LENGTH_LONG).show();
                }else{
                    String ip = txtip.getText().toString();
                    dataPreference.StorePrefrence("ipServerAddress",ip);
                    Intent intent = new Intent(ConnectActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ConnectActivity.this, DashBoardActivity.class);
        startActivity(intent);
        finish();
    }

}
