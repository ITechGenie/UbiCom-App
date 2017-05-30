package com.itechgenie.apps.ubicomclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.itechgenie.apps.ubicomclient.utils.ITGConstants;

public class MainActivity extends AppCompatActivity implements ITGConstants {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Boolean isLoggedIn = intent.getBooleanExtra(LOGGED_IN_USER, false);
        Log.d(TAG, "Switched activity - isLoggedIn: " + isLoggedIn );

    }
}
