package com.coursera.app.pm.mascotitas.presenter;

import android.content.Context;
import android.util.Log;

import com.coursera.app.pm.mascotitas.PerfilFragment;
import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.restApi.ConstantesRestApi;
import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.session.SessionManager;
import com.coursera.app.pm.mascotitas.view.IHomeFragmentView;
import com.coursera.app.pm.mascotitas.view.IPerfilFragmentView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by calyr on 1/2/17.
 */
public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {
    private static final String TAG = PerfilFragment.class.getSimpleName();
    private Context contexto;
    private ArrayList<Mascota> mascotas;
    private IPerfilFragmentView iPerfilFragmentView;
    SessionManager session;

    public PerfilFragmentPresenter( IPerfilFragmentView iPerfilFragmentView,Context contexto) {
        this.contexto = contexto;
        this.iPerfilFragmentView = iPerfilFragmentView;
        session = new SessionManager(contexto);
        System.out.println("Inicio el perfilFragmentePresenter");
        if(session.isLoggedIn() == true){
            System.out.println("Los datos de carga son" + session.getUser().toString());
            System.out.println(session.getUser().get("USERNAMEID"));
            this.obtenerMediosRecientes();
        }else{
            System.out.println("No ingreso por que no tiene session");
        }


    }

    @Override
    public void mostrarMascotasRV() {
        iPerfilFragmentView.initAdapterRvHome(iPerfilFragmentView.createAdapter(mascotas));

    }

    @Override
    public void obtenerMediosRecientes() {
        Log.d(TAG, "obtenerMediosRecientes: init");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.contruyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran(gsonMediaRecent);
        Log.d(TAG, "mEtodo CON ID " + session.getUser().get("USERNAMEID")  );
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaTest(session.getUser().get("USERNAMEID"));
        Log.d(TAG, "mEtodo CON ID " + session.getUser().get("USERNAMEID")  );
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
