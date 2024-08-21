package com.studentgrades.studentgrades.dto;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapping generic http response
 */
@Component
public class GenericResponseDto {

    /**
     * Create generic http response
     * @param data an object that contain the data to be retorned in the body
     * @param success a bolean that represent the status of the response (api)
     * @param mssg a string that contain a mssg from the api
     * @param total an int that contain the total of data obtain from de api
     * @return a string that contain the response of the api
     */
    public String genericResponseDto(Object data, boolean success, String mssg, int total) throws Exception {
        try {
            //Set object response
            Map<String, Object> response = new HashMap<>();
            response.put("data", data);
            response.put("success", success);
            response.put("mssg", mssg);
            response.put("total", total);

            //Return
            Gson gson = new Gson();
            return gson.toJson(response);
        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception(" while creating http generic api response: " + e.getMessage());
        }
    }

    /**
     * Create generic http response
     * @param data an object that contain the data to be retorned in the body
     * @param success a bolean that represent the status of the response (api)
     * @param mssg a string that contain a mssg from the api
     * @return a string that contain the response of the api
     */
    public String genericResponseDto(Object data, boolean success, String mssg) throws Exception {
        try {
            //Set object response
            Map<String, Object> response = new HashMap<>();
            response.put("data", data);
            response.put("success", success);
            response.put("mssg", mssg);

            //Return
            Gson gson = new Gson();
            return gson.toJson(response);
        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception(" while creating http generic api response: " + e.getMessage());
        }
    }
}
