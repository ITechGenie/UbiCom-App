package com.itechgenie.apps.ubicomclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itechgenie.apps.ubicomclient.R;
import com.itechgenie.apps.ubicomclient.RoomsActivity;
import com.itechgenie.apps.ubicomclient.dto.RoomDTO;

/**
 * Created by Prakash-hp on 03-06-2017.
 */

public class RoomsAdapter extends ArrayAdapter<RoomDTO> {

    private static final String LOGGER_NAME = "ProfileArrayAdapter";

    Context mContext;
    int layoutResourceId;
    RoomDTO data[] = null;

    public RoomsAdapter(Context mContext, int layoutResourceId, RoomDTO[] data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        RoomDTO objectItem = data[position];

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView roomDetailsId = (TextView) convertView.findViewById(R.id.roomsDetailsId);
        //roomDetailsId.setText("Room No: " + objectItem.getRoomNo() + " - Color: " + objectItem.getRoomColor() + " - Temperature: " + objectItem.getRoomTemp() );
        roomDetailsId.setText("Room No: " + objectItem.getRoomNo() );
        roomDetailsId.setTag("name_id_" + objectItem.getId());

        return convertView;

    }

    public void onClick(View view, int position, Long id, Object item) {

        Log.d("RoomsAdapter", "Position: " + position + " - ID: " + id ) ;
        // int position=(Integer) v.getTag();
        RoomDTO roomDTO= (RoomDTO)getItem(position);

        Intent intent= new Intent(this.mContext, RoomsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(ITGConstants.ROOM_INFORMATION, roomDTO);
        intent.putExtras(b);
        mContext.startActivity(intent);

    }
}
