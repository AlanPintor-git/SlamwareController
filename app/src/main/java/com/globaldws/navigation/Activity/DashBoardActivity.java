package com.globaldws.navigation.Activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.globaldws.navigation.Classes.DataPreference;
import com.globaldws.navigation.Helper.Global;
import com.globaldws.navigation.R;

import java.io.IOException;
import java.net.Socket;

public class DashBoardActivity extends AppCompatActivity {

    Button btnsetipserver;
    DataPreference dataPreference;
    String android_id;
    Button btnsenddata;
    boolean isconnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        dataPreference = new DataPreference(getApplicationContext());

        btnsetipserver = findViewById(R.id.setipserver);

        btnsetipserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, ConnectActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (!dataPreference.GetStoredPrefrence("ipServerAddress").equals("N/A")) {

            btnsenddata = findViewById(R.id.sendButton);
            android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            dataPreference = new DataPreference(getApplicationContext());

            new Thread(new ClientThread()).start();

        } else {
            isconnected = false;
            Toast.makeText(this, "Error Connection to Amy Plus, due to absence of server ip", Toast.LENGTH_LONG).show();
        }

        btnsenddata = findViewById(R.id.sendButton);

        btnsenddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("###8", "isconnected onClick...: " + isconnected); //false
                if (isconnected) {
                    try {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                EditText et = (EditText) findViewById(R.id.EditText01);
                                String str = et.getText().toString();
                                Log.d("###8", "before sendDsta()");
                                Global.sendData(Global.socket, str);
                                et.setText("");
                            }
                        });
                        thread.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("###8", "Error Connection to Server");
                    Toast.makeText(getApplicationContext(), "Error Connection to Server", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                Log.d("###8", "GetStoredPrefrence(\"ipServerAddress\")" + dataPreference.GetStoredPrefrence("ipServerAddress"));
                Global.socket = new Socket(dataPreference.GetStoredPrefrence("ipServerAddress"), Global.SERVERPORT);
                Global.sendData(Global.socket, "0" + Global.delimiter + android_id);
                isconnected = true;
            } catch (IOException e1) {
                isconnected = false;
                Log.d("###8", "isconnected: " + isconnected);
                //Toast.makeText(getApplicationContext(), "Error Connecting To Server", Toast.LENGTH_LONG).show();
                e1.printStackTrace();
            }
        }
    }

}
