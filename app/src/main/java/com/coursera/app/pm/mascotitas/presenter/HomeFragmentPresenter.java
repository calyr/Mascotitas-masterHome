package com.coursera.app.pm.mascotitas.presenter;


import android.content.Context;
import android.util.Log;
import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.db.ManagerMascotas;
import com.coursera.app.pm.mascotitas.restApi.ConstantesRestApi;
import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.view.IHomeFragmentView;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by calyr on 7/10/16.
 */
public class HomeFragmentPresenter implements IHomeFragmentPresenter {

//    private static final String TAG = HomeFragmentPresenter.class.getSimpleName() ;
    private static final String TAG = "CARGANDO LOS DATOS";

    private IHomeFragmentView iHomeFragmentView;
    private Context contexto;
    private ManagerMascotas manager;
    private ArrayList<Mascota> mascotas;
    public HomeFragmentPresenter(IHomeFragmentView iHomeFragmentView, Context contexto) {
        this.iHomeFragmentView = iHomeFragmentView;
        this.contexto = contexto;
        //this.obtenerMascotasBaseDatos();
        Log.d(TAG,"MEDIOS RECIENTES");
        this.obtenerMediosRecientes();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        manager = new ManagerMascotas(contexto);
       // mascotas = manager.allMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iHomeFragmentView.initAdapterRvHome(iHomeFragmentView.createAdapter(mascotas));

    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.contruyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();
        Log.d(TAG, "mEtodo" );
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
               MascotaResponse lista = response.body();
               Log.d(TAG, "INGRESO AL WEB SERVICE");
                Log.d(TAG, lista.getMascotas().toString());
                Log.d(TAG, lista.getMascotas().size() + " === ");
               //Log.d(TAG, lista.getMascotas().toString());
                Log.d(TAG, ConstantesRestApi.URL_BASE);
                Log.d(TAG, ConstantesRestApi.URL_GET_RECENT_MEDIA_USER);
                mascotas = lista.getMascotas();
                mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Log.d(TAG, "Ocurrio un error");
                Log.e(TAG, t.toString());

            }
        });
    }




}
