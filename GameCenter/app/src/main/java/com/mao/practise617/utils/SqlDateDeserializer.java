package com.mao.practise617.utils;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDateDeserializer implements JsonDeserializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, com.google.gson.JsonDeserializationContext context) throws JsonParseException {
        try {
            // Correctly parse the date string into java.sql.Date
            return new Date(dateFormat.parse(json.getAsString()).getTime());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}
