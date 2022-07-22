package com.globaldws.navigation.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.globaldws.navigation.Classes.DataPreference;
import com.globaldws.navigation.R;
import com.slamtec.slamware.discovery.DeviceManager;

public class SplashActivity extends AppCompatActivity {

    DataPreference dataPreference;
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dataPreference = new DataPreference(getApplicationContext());

        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!dataPreference.GetStoredPrefrence("ipAddress").equals("N/A")) {
                    try {
                        MainActivity.robotPlatform = DeviceManager.connect(dataPreference.GetStoredPrefrence("ipAddress"), 1445);
                        if (MainActivity.robotPlatform == null) {
                            Toast.makeText(getApplicationContext(), "Connect null", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Successfully Connected", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed Connection Try again Later", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },5000);

    }
}
