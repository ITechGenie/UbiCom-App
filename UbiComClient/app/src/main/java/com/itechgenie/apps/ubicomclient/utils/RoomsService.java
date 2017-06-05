package com.itechgenie.apps.ubicomclient.utils;

import android.util.Log;

import com.itechgenie.apps.ubicomclient.dto.RoomDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Prakash-hp on 03-06-2017.
 */

public class RoomsService {

    final static String LOGGER_TAG = "RoomsService";

    private static RoomsService roomsService = null ;

    public static RoomsService object() {

        if(roomsService == null){
            roomsService = new RoomsService();
        }
        return roomsService ;
    }

    public void loadAllRooms () {

    }
    public List<RoomDTO> loadAvailableRooms () throws Exception {

        Map<String, Object> headers = new HashMap<String, Object>() ;
        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");
        // 10.229.186.84
        String url ="http://192.168.56.1:9081/hotelmgmtservice/room/available/"  ;
        List<LinkedHashMap> respList = ITGRestClient.get(url, headers, null, List.class );

        List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>() ;

        for (LinkedHashMap hashMap: respList ) {
            RoomDTO roomDTO = (RoomDTO) ITGUtility.castObject(hashMap, RoomDTO.class) ;
            roomDTOs.add(roomDTO) ;
        }
        Log.d(LOGGER_TAG, "Obtained response : " + roomDTOs);
        return roomDTOs ;
    }

    public void   fetchRoom( ) {

    }

    public boolean saveBooking (RoomDTO roomDTO) throws Exception {

        Log.d(LOGGER_TAG, "saveBooking : Start");
        Map<String, Object> headers = new HashMap<String, Object>() ;
        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");
        String url ="http://192.168.56.1:9081/hotelmgmtservice/room/book/";

        try {

            String response = ITGRestClient.post(url, headers, roomDTO, String.class);
            Log.d(LOGGER_TAG, "saveBooking : response received --> " + response) ;

        } catch (Exception e) {
            Log.d(LOGGER_TAG, "Exception occured :" + e.getMessage());
            return false;
        }
        Log.d(LOGGER_TAG, "saveBooking : End");
        return true;
    }


}
