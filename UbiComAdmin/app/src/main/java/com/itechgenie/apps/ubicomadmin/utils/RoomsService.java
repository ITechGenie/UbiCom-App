package com.itechgenie.apps.ubicomadmin.utils;

import android.util.Log;

import com.itechgenie.apps.ubicomadmin.dto.RoomDTO;

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

    private static RoomsService roomsService = null;

    public static RoomsService object() {

        if (roomsService == null) {
            roomsService = new RoomsService();
        }
        return roomsService;
    }

    public void loadAllRooms() {

    }

    public List<RoomDTO> loadAvailableRooms() throws Exception {

        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");
        // 10.229.186.84
        String url = ITGConstants.APP_SERVER_HOST_NAME + "/room/all";
        List<LinkedHashMap> respList = ITGRestClient.get(url, headers, null, List.class);

        List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();

        for (LinkedHashMap hashMap : respList) {
            RoomDTO roomDTO = (RoomDTO) ITGUtility.castObject(hashMap, RoomDTO.class);
            roomDTOs.add(roomDTO);
        }
        Log.d(LOGGER_TAG, "Obtained response : " + roomDTOs);
        return roomDTOs;
    }

    public void fetchRoom() {

    }

    public boolean saveRoom(RoomDTO roomDTO) throws Exception {

        Log.d(LOGGER_TAG, "saveBooking : Start");
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");

        String url = "";


        // Saving Room Color

        if (roomDTO.getUserEmail() != null && !"null".equalsIgnoreCase(roomDTO.getUserEmail())
                && !"admin@ubicom.com".equalsIgnoreCase(roomDTO.getUserEmail())
                && !"UNBOKKED".equalsIgnoreCase(roomDTO.getUserEmail())) {

            if ("DUMMY".equalsIgnoreCase(roomDTO.getUserEmail())) {
                roomDTO.setUserEmail(null);
            }

            url = ITGConstants.APP_SERVER_HOST_NAME + "/room/book";

            try {

                Map response = ITGRestClient.post(url, headers, roomDTO, Map.class);
                Log.d(LOGGER_TAG, "saveEmail : response received --> " + response);

            } catch (Exception e) {
                Log.d(LOGGER_TAG, "Exception occurred :" + e.getMessage());
                return false;
            }

            Log.d(LOGGER_TAG, "Room vacated !");

            return true;
        }

        if (roomDTO.getRoomTemp() != null) {

            url = ITGConstants.APP_SERVER_HOST_NAME + "/room/temperature";

            try {

                Map response = ITGRestClient.post(url, headers, roomDTO, Map.class);
                Log.d(LOGGER_TAG, "saveTemp : response received --> " + response);

            } catch (Exception e) {
                Log.d(LOGGER_TAG, "Exception occurred :" + e.getMessage());
                return false;
            }
        }

        if (roomDTO.getRoomColor() != null) {

            url = ITGConstants.APP_SERVER_HOST_NAME + "/room/color";

            try {

                Map response = ITGRestClient.post(url, headers, roomDTO, Map.class);
                Log.d(LOGGER_TAG, "saveColor : response received --> " + response);

            } catch (Exception e) {
                Log.d(LOGGER_TAG, "Exception occurred :" + e.getMessage());
                return false;
            }
        }

        Log.d(LOGGER_TAG, "saveRoom : End");
        return true;
    }


}
