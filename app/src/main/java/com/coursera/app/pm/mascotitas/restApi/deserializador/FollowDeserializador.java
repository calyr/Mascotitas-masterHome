package com.coursera.app.pm.mascotitas.restApi.deserializador;

import android.util.Log;

import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.restApi.JsonKeys;
import com.coursera.app.pm.mascotitas.restApi.model.FollowResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.coursera.app.pm.mascotitas.session.SessionManager;
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
 * Created by calyr on 1/2/17.
 */
public class FollowDeserializador implements JsonDeserializer<FollowResponse>{
        private static final String TAG = "CARGANDO";
    SessionManager session;
    @Override
    public FollowResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowResponse mascotaResponse = gson.fromJson(json,FollowResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setSeguidores(deserializarMascotaDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<UsuarioResponse> deserializarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<UsuarioResponse> mascotas = new ArrayList<>();
        System.out.println("El size es "+ mascotaResponseData.size() + " - " );
        for (int i = 0; i < mascotaResponseData.size(); i++){
              Log.d(TAG, "PRIMERO OBJETOS E" + new Gson().toJson(mascotaResponseData) );
             JsonObject jsonObjectData = mascotaResponseData.get(i).getAsJsonObject();
             String id = jsonObjectData.get(JsonKeys.USER_ID).getAsString() ;//userJson.get(JsonKeys.USER_ID).getAsString();
            String urlFoto                  = jsonObjectData.get(JsonKeys.PROFILE_PICTURE).getAsString();
            String nombreCompleto                  = jsonObjectData.get(JsonKeys.FULL_NAME).getAsString();
            String userName                  = jsonObjectData.get(JsonKeys.USERNAME).getAsString();

            UsuarioResponse usuarioActual = new UsuarioResponse();
            usuarioActual.setId(id);
            usuarioActual.setUrlImage(urlFoto);
            usuarioActual.setFullName(nombreCompleto);
            usuarioActual.setUserInstagram(userName);
            Log.d(TAG, "deserializarMascotaDeJson: "+ new Gson().toJson(usuarioActual));
            mascotas.add(usuarioActual);
//



        }
        return mascotas;
    }
}
