package com.itechgenie.apps.ubicomclient.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Prakash-hp on 20-05-2017.
 */

public class ITGUtility {

    private static ObjectMapper objectMapper = new ObjectMapper( ) ;

    public static String objectToJson(Object inputObj) throws IOException {
        return objectMapper.writeValueAsString(inputObj) ;
    }

    public static Object jsonToObject(String jsonString, Class className) throws IOException {
        return objectMapper.readValue(jsonString, className) ;
    }

    public static Object castObject(Object object, Class className) throws IOException {
        return objectMapper.readValue(objectToJson(object), className) ;
    }

    public static boolean isNotNull(Object input)  {
        if (input == null ) return false ;
        if ("".equalsIgnoreCase(input.toString().trim())) return false;
        return true ;
    }
}
