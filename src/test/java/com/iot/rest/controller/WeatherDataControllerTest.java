package com.iot.rest.controller;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.transaction.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by basant on 8/7/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableWebMvc
@Transactional
public class WeatherDataControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Test get request
     * @throws Exception
     */
    @Test
    public void testGetWeatherData() throws Exception{
        String getWeatherDataUrl = new StringBuilder()
                .append("/")
                .append(WeatherDataController.WEATHER_DATA_URL)
                .toString();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(getWeatherDataUrl)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("failure - expected HTTP status 200", 200, result.getResponse().getStatus());
        Assert.assertTrue("failure - expected payload to have a value", content.trim().length() > 0);
    }

    /**
     * Test post request for weather data
     * @throws Exception
     */
    @Test
    public void testCreateWeatherData() throws Exception{

        String getWeatherDataUrl = new StringBuilder()
                .append("/")
                .append(WeatherDataController.WEATHER_DATA_URL)
                .toString();

        String weatherData= "{ \"deviceId\": \"1234ABCS\", \"timestamp\": \"12 July 2017 8:23:00\", \"temperature\": \"80.0\", \"percentage\": \"40\"}";

        logger.info(weatherData);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(getWeatherDataUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(weatherData))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assert.assertEquals("failure - expected HTTP status 201", 201, result.getResponse().getStatus());
        Assert.assertTrue("failure - expected payload to have a value", content.trim().length() > 0);

    }

    /**
     * Test validation error message for missing timestamp
     * @throws Exception
     */
    @Test
    public void testCreateWeatherDataValidation() throws Exception{
        String getWeatherDataUrl = new StringBuilder()
                .append("/")
                .append(WeatherDataController.WEATHER_DATA_URL)
                .toString();

        // removed timestamp key value
        String weatherData= "{ \"deviceId\": \"1234ABCS\", \"temperature\": \"80.0\", \"percentage\": \"40\"}";

        logger.info(weatherData);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(getWeatherDataUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(weatherData))
                .andExpect(jsonPath("$.status", Matchers.is("ERROR")))
                .andExpect(jsonPath("$.message", Matchers.is("Validation Error.")))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.errors.timestamp", Matchers.is("Timestamp is required.")))
                .andReturn();

        Assert.assertEquals("failure - expected HTTP status 400", 400, result.getResponse().getStatus());
    }

    /**
     * Test response body format
     * @throws Exception
     */
    @Test
    public void testGetWeatherDataWithPayload() throws Exception {
        String getWeatherDataUrl = new StringBuilder()
                .append("/")
                .append(WeatherDataController.WEATHER_DATA_URL)
                .toString();

        mockMvc.perform(MockMvcRequestBuilders.get(getWeatherDataUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.message", Matchers.is("List of weather data.")))
                .andExpect(jsonPath("$.data.weather[0].deviceId", Matchers.is("1234ABCS")))
                .andExpect(jsonPath("$.data.weather[0].timestamp", Matchers.is("2017-07-12 08:13:00")))
                .andExpect(jsonPath("$.data.weather[0].temperature", Matchers.is(75.1)))
                .andExpect(jsonPath("$.data.weather[0].percentage", Matchers.is(37)))
                .andExpect(jsonPath("$.errors").isEmpty())
            ;
    }

}
