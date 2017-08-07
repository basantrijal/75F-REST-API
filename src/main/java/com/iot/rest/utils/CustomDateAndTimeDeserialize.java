package com.iot.rest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by basant on 8/7/17.
 */
public class CustomDateAndTimeDeserialize extends JsonDeserializer<Date>{

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US);

    @Override
    public Date deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String str = jsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {

        }
        return deserializationContext.parseDate(str);
    }
}
