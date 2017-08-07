package com.iot.rest.response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by basant on 8/7/17.
 */
public class ApiResponse {

    private String status;
    private String message;
    private HashMap<String, Object> data = new HashMap<String, Object>();
    private HashMap<String, String> errors = new HashMap<String, String>();

    public ApiResponse(String status, String message){
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(String name, Object data) {
        this.data.put(name, data);
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(String field, String message) {
        this.errors.put(field, message);
    }
}
