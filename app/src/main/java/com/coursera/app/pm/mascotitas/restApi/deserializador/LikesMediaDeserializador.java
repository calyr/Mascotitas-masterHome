package com.coursera.app.pm.mascotitas.restApi.deserializador;

import android.util.Log;

import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.restApi.JsonKeys;
import com.coursera.app.pm.mascotitas.restApi.model.LikesMedia;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by calyr on 12/26/16.
 */
public class LikesMediaDeserializador implements JsonDeserializer<LikesMedia> {

    private static final String TAG = "LIKES CLASS";

    @Override
    public LikesMedia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikesMedia mascotaResponse;
        JsonObject mascotaResponseData = json.getAsJsonObject();
        mascotaResponse = deserializarMascotaDeJson(mascotaResponseData);
        return mascotaResponse;
    }

    private LikesMedia deserializarMascotaDeJson(JsonObject mascotaResponseData){
        LikesMedia likesMedia = new LikesMedia();

            Log.d(TAG, " deserializarMascotaDeJson  " + new Gson().toJson(mascotaResponseData) );
            JsonObject meta = mascotaResponseData.get(JsonKeys.META).getAsJsonObject();

            int code = meta.get(JsonKeys.CODE).getAsInt();
            likesMedia.setCode(code);


        Log.d(TAG, "deserializarMascotaDeJson: result" + new Gson().toJson(likesMedia));

        return likesMedia;
    }
}
