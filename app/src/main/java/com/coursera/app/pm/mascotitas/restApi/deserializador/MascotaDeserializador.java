package com.coursera.app.pm.mascotitas.restApi.deserializador;

import android.util.Log;

import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.restApi.JsonKeys;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
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
 * Created by calyr on 11/27/16.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

    private static final String TAG = "CARGANDO";

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(deserializarMascotaDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++){
            Log.d(TAG, "PRIMERO OBJETOS E" + new Gson().toJson(mascotaResponseData) );
           JsonObject mascotaResponseDatObject = mascotaResponseData.get(i).getAsJsonObject();


           JsonObject userJson = mascotaResponseDatObject.getAsJsonObject(JsonKeys.USER     );
           String id = mascotaResponseDatObject.get(JsonKeys.USER_ID).getAsString() ;//userJson.get(JsonKeys.USER_ID).getAsString();
           String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

           JsonObject imageJson            = mascotaResponseDatObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
           JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
           String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

           JsonObject likesJson = mascotaResponseDatObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
           int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setImagen(urlFoto);
            mascotaActual.setNombre(nombreCompleto);
            mascotaActual.setLikes(likes);
            Log.d(TAG,mascotaActual.cadena());
            Log.d(TAG, "deserializarMascotaDeJson: "+ new Gson().toJson(mascotaActual));
            mascotas.add(mascotaActual);




        }
        return mascotas;
    }
}
