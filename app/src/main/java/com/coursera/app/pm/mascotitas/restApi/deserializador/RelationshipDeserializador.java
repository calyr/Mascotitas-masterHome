package com.coursera.app.pm.mascotitas.restApi.deserializador;

import android.util.Log;

import com.coursera.app.pm.mascotitas.restApi.JsonKeys;
import com.coursera.app.pm.mascotitas.restApi.model.RelationshipResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by calyr on 1/2/17.
 */
public class RelationshipDeserializador implements JsonDeserializer<RelationshipResponse> {
	private static final String TAG = UsuarioDeserializador.class.getSimpleName();

	@Override
	public RelationshipResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Gson gson = new Gson();
		RelationshipResponse relationshipResponse;
		JsonObject relationshipResponseData = json.getAsJsonObject();
		relationshipResponse = deserializarRelationshipDeJson(relationshipResponseData);
		return relationshipResponse;
	}

	private RelationshipResponse deserializarRelationshipDeJson(JsonObject relationshipResponseData){
		RelationshipResponse relationshipMedia = new RelationshipResponse();

		Log.d(TAG, " RelationshipResponse  " + new Gson().toJson(relationshipResponseData) );
		JsonObject meta = relationshipResponseData.get(JsonKeys.META).getAsJsonObject();
		if (meta != null){
			int code = meta.get(JsonKeys.CODE).getAsInt();
			if( code == 200){
				JsonObject data = relationshipResponseData.get(JsonKeys.DATA).getAsJsonObject();
				if (data != null){

					if (data.has(JsonKeys.OUTGOING)){
						relationshipMedia.setIncomingStatus(data.get(JsonKeys.OUTGOING).getAsString());
					}
					if (data.has(JsonKeys.TARGET_USER)){
						relationshipMedia.setTargetUserIsPrivate(data.get(JsonKeys.TARGET_USER).getAsBoolean());
					}

					if (data.has(JsonKeys.INCOMING)){
						relationshipMedia.setIncomingStatus(data.get(JsonKeys.INCOMING).getAsString());
					}

				}
			}
		}

		Log.d(TAG, "RelationshipResponse: result" + new Gson().toJson(relationshipMedia));

		return relationshipMedia;
	}
}
