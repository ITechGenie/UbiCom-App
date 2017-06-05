package com.itechgenie.apps.ubicomadmin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itechgenie.apps.ubicomadmin.R;
import com.itechgenie.apps.ubicomadmin.RoomsActivity;
import com.itechgenie.apps.ubicomadmin.dto.RoomDTO;

/**
 * Created by Prakash-hp on 03-06-2017.
 */

public class RoomsAdapter extends ArrayAdapter<RoomDTO> {

    private static final String LOGGER_NAME = "ProfileArrayAdapter";
    private static boolean IS_LOGGED_IN;
    private static String USER_NAME;
    private static String USER_EMAIL_ID;

    Context mContext;
    int layoutResourceId;
    RoomDTO data[] = null;

    public RoomsAdapter(Context mContext, int layoutResourceId, RoomDTO[] data, Boolean isLoggedIn, String userName, String userEmail) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        IS_LOGGED_IN = isLoggedIn;
        USER_NAME = userName;
        USER_EMAIL_ID = userEmail;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        RoomDTO objectItem = data[position];

        View viewL = (View) convertView.findViewById(R.id.rooms_list_layout);
        viewL.setBackgroundColor(getColorInt(objectItem.getRoomColor()));

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView roomDetailsId = (TextView) convertView.findViewById(R.id.roomsDetailsId);
        String bookingStatus = "BOOKED" ;
        //roomDetailsId.setText("Room No: " + objectItem.getRoomNo() + " - Color: " + objectItem.getRoomColor() + " - Temperature: " + objectItem.getRoomTemp() );
        if (objectItem.getUserEmail() == null || "null".equalsIgnoreCase(objectItem.getUserEmail()))
            bookingStatus = "UNBOKKED" ;
        roomDetailsId.setText("Room No: " + objectItem.getRoomNo() + " - " + bookingStatus);
        roomDetailsId.setTag("name_id_" + objectItem.getId());

        return convertView;

    }

    public void onClick(View view, int position, Long id, Object item) {

        Log.d("RoomsAdapter", "Position: " + position + " - ID: " + id);
        // int position=(Integer) v.getTag();
        RoomDTO roomDTO = (RoomDTO) getItem(position);


        Intent intent = new Intent(this.mContext, RoomsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(ITGConstants.ROOM_INFORMATION, roomDTO);
        b.putSerializable("IS_LOGGED_IN", IS_LOGGED_IN);
        b.putSerializable("USER_NAME", USER_NAME);
        b.putSerializable("USER_EMAIL_ID", USER_EMAIL_ID);

        intent.putExtras(b);
        mContext.startActivity(intent);

    }

    private int getColorInt(String colorText) {

        int colorCode = Color.LTGRAY;

        if ("RED".equalsIgnoreCase(colorText)) {
            colorCode = Color.RED;
        }
        if ("GREEN".equalsIgnoreCase(colorText)) {
            colorCode = Color.GREEN;
        }
        if ("BLUE".equalsIgnoreCase(colorText)) {
            colorCode = Color.BLUE;
        }
        if ("YELLOW".equalsIgnoreCase(colorText)) {
            colorCode = Color.YELLOW;
        }
        if ("MAGENTA".equalsIgnoreCase(colorText)) {
            colorCode = Color.MAGENTA;
        }
        if ("CYAN".equalsIgnoreCase(colorText)) {
            colorCode = Color.CYAN;
        }

        return colorCode;

    }

}
