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
        String url ="http://192.168.1.238:9080/hotelmgmtservice/room/available/"  ;
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
}
