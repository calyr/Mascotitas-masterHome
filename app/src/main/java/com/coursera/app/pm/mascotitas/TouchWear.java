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
        String ACCION_KEY_UNFOLLOW = "UNFOLLOW";
        String ACCION_KEY_PERFIL = "PERFIL";
        String ACCION_KEY_HOME = "HOME";
        String accion = intent.getAction();
        if  ( ACCION_KEY_FOLLOW.equals(accion)){

            Toast.makeText(context, "follow", Toast.LENGTH_SHORT ).show();
            followUnfollow(context,intent,"follow");
        }else if(ACCION_KEY_UNFOLLOW.equals(accion)){
            Toast.makeText(context, "unfollow", Toast.LENGTH_SHORT ).show();
            followUnfollow(context,intent,"unfollow");

        }else if(ACCION_KEY_PERFIL.equals(accion)){
            Toast.makeText(context, "Habriendo el perfil del usuario configurado", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent();
            i.setClassName("com.coursera.app.pm.mascotitas", "com.coursera.app.pm.mascotitas.MainActivity");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("FLAGTAB","TWO");

            context.startActivity(i);


        }else if(ACCION_KEY_HOME.equals(accion)){
            Toast.makeText(context, "Habriendo el home", Toast.LENGTH_SHORT ).show();
            Intent i = new Intent();
            i.setClassName("com.coursera.app.pm.mascotitas", "com.coursera.app.pm.mascotitas.MainActivity");
            i.putExtra("FLAGTAB","ONE");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);


        }
        //followUnfollow();
    }

    public void followUnfollow(final Context context, Intent intent, final  String type){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonRelationship = restApiAdapter.contruyeGsonDeserializadorRelationship();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran(gsonRelationship);
        SessionManager session = new SessionManager(context.getApplicationContext());
        String userid = session.getUser().get("USERNAMEID");
        System.out.println("La configuracion del usuario es ==" + session.getUser() + " ==");
        System.out.println(userid);
        if( userid != "" ){

            Call<RelationshipResponse> relationshipResponseCall = endpointsApi.setRelationship(userid,type);
            Log.d(TAG, "MascotaAdapter imagen" );
            relationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
                @Override
                public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {

                    if (response.isSuccessful()){

                        RelationshipResponse lista = response.body();
                        Log.d(TAG, "onResponse: " + new Gson().toJson(lista));
                        Toast.makeText(context, " "+ type +" success", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(context, "Ocurrio un problema porfavor vuelva a intentar", Toast.LENGTH_LONG).show();

                        Log.d(TAG, "onResponse: ERROR" + new Gson().toJson(response.errorBody()));
                        //                               response.errorBody()
                    }

                }
                @Override
                public void onFailure(Call<RelationshipResponse> call, Throwable t) {
                    Log.d(TAG, "Ocurrio un error");
                    Log.e(TAG, t.toString());
                    Toast.makeText(context, R.string.failure, Toast.LENGTH_LONG).show();


                }
            });
        }else{
            Toast.makeText(context, R.string.error_follow, Toast.LENGTH_LONG).show();
        }

    }


}
