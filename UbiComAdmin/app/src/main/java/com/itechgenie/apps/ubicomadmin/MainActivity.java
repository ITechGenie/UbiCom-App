package com.itechgenie.apps.ubicomadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.itechgenie.apps.ubicomadmin.utils.ITGConstants;

public class MainActivity extends AppCompatActivity implements ITGConstants {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
