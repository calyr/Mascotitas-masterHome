package com.coursera.app.pm.mascotitas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.RelationshipResponse;
import com.coursera.app.pm.mascotitas.session.SessionManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by calyr on 1/1/17.
 */
public class TouchWear extends BroadcastReceiver {
    private static final String TAG = TouchWear.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY_FOLLOW = "FOLLOW";
        String ACCION_KEY_PERFIL = "PERFIL";
        String ACCION_KEY_HOME = "HOME";
        String accion = intent.getAction();
        if  ( ACCION_KEY_FOLLOW.equals(accion)){
            followUnfollow(context,intent);
            Toast.makeText(context, "Follow/Unfollow", Toast.LENGTH_SHORT ).show();
        }else if(ACCION_KEY_PERFIL.equals(accion)){
            Toast.makeText(context, "Habriendo el perfil del usuario configurado", Toast.LENGTH_SHORT ).show();
            openPerifl(context, intent);

        }else if(ACCION_KEY_HOME.equals(accion)){
            Toast.makeText(context, "Habriendo el home", Toast.LENGTH_SHORT ).show();
            openHome(context, intent);

        }
        //followUnfollow();
    }

    public void followUnfollow(Context context, Intent intent){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonRelationship = restApiAdapter.contruyeGsonDeserializadorRelationship();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran(gsonRelationship);
        SessionManager session = new SessionManager(context.getApplicationContext());
        String userid = session.getUser().get("USERNAMEID");

        if( userid != "" ){
            Toast.makeText(context, R.string.error_follow, Toast.LENGTH_LONG).show();

            Call<RelationshipResponse> relationshipResponseCall = endpointsApi.setRelationship(userid,"follow");
            Log.d(TAG, "MascotaAdapter imagen" );
            relationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
                @Override
                public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {

                    Log.d(TAG, "INGRESO AL WEB SERVICE TOUCHWEAR");
                    if (response.isSuccessful()){
                        Log.d(TAG, "OKEY");

                        RelationshipResponse lista = response.body();
                        Log.d(TAG, "onResponse: " + new Gson().toJson(lista));
                    }else{

                        Log.d(TAG, "onResponse: ERROR" + new Gson().toJson(response.errorBody()));
                        //                               response.errorBody()
                        Log.d(TAG, "onResponse: NOOKE");
                    }

                }
                @Override
                public void onFailure(Call<RelationshipResponse> call, Throwable t) {
                    Log.d(TAG, "Ocurrio un error");
                    Log.e(TAG, t.toString());

                }
            });
        }else{
            Toast.makeText(context, R.string.error_follow, Toast.LENGTH_LONG).show();
        }

    }

    public void openPerifl(Context context, Intent intent){
        Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
        intentone.putExtra("flagtab","ONE");
        context.startActivity(intentone);


    }

    public void openHome(Context context, Intent intent){
        Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
        intentone.putExtra("flagtab","TWO");
        context.startActivity(intentone);

    }
}
