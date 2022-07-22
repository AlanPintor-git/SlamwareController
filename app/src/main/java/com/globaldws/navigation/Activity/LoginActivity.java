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
import com.slamtec.slamware.discovery.DeviceManager;

public class LoginActivity extends AppCompatActivity {

    private EditText ip_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DataPreference dataPreference = new DataPreference(getApplicationContext());

        ip_address = (EditText) findViewById(R.id.ip_address);

        String digits = "0123456789.";
        ip_address.setInputType(InputType.TYPE_CLASS_NUMBER);
        ip_address.setKeyListener(DigitsKeyListener.getInstance(digits));

        Button buttonLogin = (Button) findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ip_address.length() == 0 || ip_address.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Error ip address", Toast.LENGTH_LONG).show();
                } else {

                    String ip = ip_address.getText().toString();

                    try {
                        MainActivity.robotPlatform = DeviceManager.connect(ip, 1445);
                        if (MainActivity.robotPlatform == null) {
                            Toast.makeText(LoginActivity.this, "Connect null", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LoginActivity.this, "Successfully Connected", Toast.LENGTH_SHORT).show();

                            dataPreference.StorePrefrence("ipAddress", ip);

                            Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Failed Connection Try again Later", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
