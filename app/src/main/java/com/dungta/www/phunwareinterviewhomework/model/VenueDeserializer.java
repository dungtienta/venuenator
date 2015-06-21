package com.dungta.www.phunwareinterviewhomework.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Class used for deserializing the nested schedule json object
 */
public class VenueDeserializer implements JsonDeserializer <ScheduleItem> {
    @Override
    public ScheduleItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonElement scheduleItem = json.getAsJsonObject().get("schedule");

        return new Gson().fromJson(scheduleItem, ScheduleItem.class);
    }
}
