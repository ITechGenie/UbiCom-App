package com.itechgenie.apps.ubicomclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.itechgenie.apps.ubicomclient.dto.RoomDTO;
import com.itechgenie.apps.ubicomclient.utils.ITGConstants;
import com.itechgenie.apps.ubicomclient.utils.RoomsAdapter;
import com.itechgenie.apps.ubicomclient.utils.RoomsAsyncLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ITGConstants, RoomsAsyncLoader.callBack {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Boolean isLoggedIn = intent.getBooleanExtra(LOGGED_IN_USER, false);
        String userName = intent.getStringExtra(LOGGED_IN_USER_NAME);
        String emailId = intent.getStringExtra(LOGGED_IN_USER_EMAIL);

        Log.d(TAG, "Switched activity - isLoggedIn: " + isLoggedIn + " - userName: " + userName + " - emailId: " + emailId);

        TextView textView = (TextView) findViewById(R.id.welcomeTxtId);
        textView.setText(userName + " - " + emailId);

        new RoomsAsyncLoader(MainActivity.this).execute();

    }

    @Override
    public void returnText(Object value) {
        Log.d(TAG, "Obtained response: " + value);

        List<RoomDTO> rooms = (List<RoomDTO>) value;

        if (value != null && rooms.size() > 0) {
            ListView availableRooms = (ListView) findViewById(R.id.availableRoomsListId);

            final RoomDTO roomsArray[] = rooms.toArray(new RoomDTO[rooms.size()]);

            availableRooms.setAdapter(new RoomsAdapter(this, R.layout.rooms_list_layout, roomsArray));

            availableRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ((RoomsAdapter) parent.getAdapter()).onClick(view, position, id, roomsArray[position]);
                }
            });
        }

    }
}
