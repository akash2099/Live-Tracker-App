package com.example.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    boolean data_present=false;
    String UsernameData,PhoneNumberData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        navigate_next();

        //if data not null in shared preference then data_present=true, else nothing.
        SharedRef sharedRef=new SharedRef(this);
//        sharedRef.SaveDataSharedRef("no username","no phone number");

        UsernameData=sharedRef.LoadDataUsername();
        PhoneNumberData=sharedRef.LoadDataPhoneNumber();
        data_present= (!UsernameData.equalsIgnoreCase("no username")) && (!PhoneNumberData.equalsIgnoreCase("no phone number"));
        System.out.println(UsernameData+" : "+PhoneNumberData);

    }

    public void navigate_next(){
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {

                if (data_present) {
                    Intent inten = new Intent(getApplicationContext(), Main4ActivityMain.class);
                    startActivity(inten);
                    finish();
                    Toast.makeText(getApplicationContext(), "Login Successful "+UsernameData+" : "+PhoneNumberData, Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent inten = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(inten);
                    finish();
                }
                System.out.println("Starting main activity");
            }
        };
        handler.postDelayed(r, 1500);
   }

}
