package com.coursera.app.pm.mascotitas.restApi.deserializador;

import android.util.Log;

import com.coursera.app.pm.mascotitas.restApi.JsonKeys;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by calyr on 1/1/17.
 */
public class UsuarioDeserializador implements JsonDeserializer<UsuarioResponse> {
    private static final String TAG = UsuarioDeserializador.class.getSimpleName();

    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse mascotaResponse;
        JsonObject mascotaResponseData = json.getAsJsonObject();
        mascotaResponse = deserializarUsuarioDeJson(mascotaResponseData);
        return mascotaResponse;
    }

    private UsuarioResponse deserializarUsuarioDeJson(JsonObject mascotaResponseData){
        UsuarioResponse likesMedia = new UsuarioResponse();

        Log.d(TAG, " UsuarioDeserializador  " + new Gson().toJson(mascotaResponseData) );
        JsonArray meta = mascotaResponseData.get(JsonKeys.DATA).getAsJsonArray();

        System.out.println("meta.size = " + meta.size());

        Log.d(TAG, "UsuarioDeserializador: result" + new Gson().toJson(likesMedia));

        return likesMedia;
    }
}
