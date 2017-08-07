package com.iot.rest.controller;

import com.iot.rest.entity.WeatherData;
import com.iot.rest.repository.WeatherDataRepository;
import com.iot.rest.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by basant on 8/7/17.
 */
@RestController
public class WeatherDataController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataController.class);

    public static final String WEATHER_DATA_URL = "weather_data";

    @Autowired
    WeatherDataRepository weatherDataRepository;

    /**
     * List all the weather data of device 1234ABCS
     * @return ResponseEntity
     */
    @RequestMapping(value="/weather_data", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getWeatherData(){
        List<WeatherData> weatherData = weatherDataRepository.findByDeviceId("1234ABCS");
        ApiResponse apiResponse = new ApiResponse("SUCCESS", "List of weather data.");
        apiResponse.setData("weather", weatherData);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     *
     * @param weatherData
     * @return ResponseEntity
     */
    @RequestMapping(
            value="/weather_data",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postWeatherData(@Valid @RequestBody WeatherData weatherData, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ApiResponse apiResponse = new ApiResponse("ERROR", "Validation Error.");
            List<FieldError> fieldErrorList= bindingResult.getFieldErrors();
            for(FieldError fieldError: fieldErrorList ){
                apiResponse.setErrors(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(apiResponse);
        }
        weatherDataRepository.save(weatherData);
        ApiResponse apiResponse = new ApiResponse("SUCCESS", "Weather data insertion completed.");
        apiResponse.setData("weather", weatherData);
        return new ResponseEntity(weatherData, HttpStatus.CREATED);
    }
}
