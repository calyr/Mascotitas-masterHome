package com.coursera.app.pm.mascotitas;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.coursera.app.pm.mascotitas.pojo.Mascota;


import com.coursera.app.pm.mascotitas.db.ManagerMascotas;
import com.coursera.app.pm.mascotitas.restApi.ConstantesRestApi;
import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.LikesMedia;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.coursera.app.pm.mascotitas.session.SessionManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by callisaya on 6/27/16.
 */
public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private static final String TAG = "MascotaAdapter" ;
    Activity activity;
    ArrayList<Mascota> mascotas;
    private int tipo = 0;
    final SessionManager session;


    public MascotaAdapter(ArrayList<Mascota> mascotas, int tipo, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;


        session =  new SessionManager(activity.getApplicationContext());

    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        if (tipo == 1) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_mascotaperfil, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_mascota, viewGroup, false);
        }



        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int i) {
        final Mascota masconta = mascotas.get(i);
//        mascotaViewHolder.imgFoto.setImageResource(contacto.getFoto() );
       // mascotaViewHolder.foto.setImageResource(masconta.getImagen());
        Picasso.with(activity)
                .load(masconta.getImagen())
                .placeholder(R.drawable.bone)
                .into(mascotaViewHolder.foto);
        mascotaViewHolder.nombre.setText(masconta.getNombre());
        mascotaViewHolder.retuit.setText(String.valueOf(masconta.getLikes()));

        mascotaViewHolder.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ManagerMascotas manager = new ManagerMascotas(activity);
                manager.retuitMascota(masconta);
//                mascotaViewHolder.retuit.setText(String.valueOf(manager.getRetuitMascota(masconta)));
//
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                Gson gsonMediaLikes = restApiAdapter.contruyeGsonDeserializadorMediaLikes();
                EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran(gsonMediaLikes);
                Call<LikesMedia> mascotaResponseCall = endpointsApi.likeImagen(masconta.getId());
                Log.d(TAG, "MascotaAdapter imagen" );
                mascotaResponseCall.enqueue(new Callback<LikesMedia>() {
                    @Override
                    public void onResponse(Call<LikesMedia> call, Response<LikesMedia> response) {

                        Log.d(TAG, "INGRESO AL WEB SERVICE");
                        if (response.isSuccessful()){
                            Log.d(TAG, "OKEY");

                            LikesMedia lista = response.body();
                            Snackbar.make(v, R.string.like_success, Snackbar.LENGTH_LONG).show();
                            Log.d(TAG, "onResponse: " + new Gson().toJson(lista));
                        }else{
                            Snackbar.make(v, R.string.like_notsuccess, Snackbar.LENGTH_LONG).show();

                            Log.d(TAG, "onResponse: ERROR" + new Gson().toJson(response.errorBody()));
 //                               response.errorBody()
                            Log.d(TAG, "onResponse: NOOKE");
                        }

                    }
                    @Override
                    public void onFailure(Call<LikesMedia> call, Throwable t) {
                        Log.d(TAG, "Ocurrio un error");
                        Log.e(TAG, t.toString());

                    }
                });
                //


                //
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, "onMessageReceived:  "+ token);
                String userInstagram =  "robertocarlos1125";

                if(session.isLoggedIn() == true){
                    userInstagram = session.getUser().get("name");
                }
                EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPI();
                Call<UsuarioResponse> usuarioResponseCall = endpoints.sendNotification(token, userInstagram,masconta.getImagen());
                Log.d(TAG, "onClick: "+ token);
                Log.d(TAG, "onClick: "+ userInstagram);
                Log.d(TAG, "onClick: " + masconta.getImagen());
                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        if(response.isSuccessful()){
                            Log.d(TAG, "onResponse Send not: OK");
                            UsuarioResponse usuarioResponse = response.body();
                            //Log.d("TOKEN FIREBASE SEND", usuarioResponse.getToken());
                        }else{
                            Log.d(TAG, "onResponse Send not: NOOK" + new Gson().toJson(response.errorBody()));
                        }

                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: Send failure");
                    }
                });
                //



            }
        });

        mascotaViewHolder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagerMascotas manager = new ManagerMascotas(activity);
                manager.retuitMascota(masconta);
                //mascotaViewHolder.retuit.setText( String.valueOf( manager.getRetuitMascota(masconta)));

                RestApiAdapter restApiAdapter = new RestApiAdapter();
                Gson gsonMediaLikes = restApiAdapter.contruyeGsonDeserializadorMediaLikes();
                EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran(gsonMediaLikes);
                Call<LikesMedia> mascotaResponseCall = endpointsApi.likeImagen(masconta.getId());
                Log.d(TAG, "mEtodo nombre" );
                mascotaResponseCall.enqueue(new Callback<LikesMedia>() {
                    @Override
                    public void onResponse(Call<LikesMedia> call, Response<LikesMedia> response) {

                        Log.d(TAG, "INGRESO AL WEB SERVICE");
                        if (response.isSuccessful()){
                            Log.d(TAG, "OKEY");

                            LikesMedia lista = response.body();
                            Log.d(TAG, "onResponse: " + new Gson().toJson(lista));
                            //

                        }else{
                            Log.d(TAG, "onResponse: NOOKE");
                        }
                    }
                    @Override
                    public void onFailure(Call<LikesMedia> call, Throwable t) {
                        Log.d(TAG, "Ocurrio un error");
                        Log.e(TAG, t.toString());

                    }
                });
                //

            }
        });


    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;
        private ImageView foto;
        private TextView retuit;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            foto = (ImageView) itemView.findViewById(R.id.imgFoto);
            retuit = (TextView) itemView.findViewById(R.id.cantidad);
        }
    }
}
