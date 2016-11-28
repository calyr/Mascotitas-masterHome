package com.coursera.app.pm.mascotitas.restApi.adapter;

import com.coursera.app.pm.mascotitas.restApi.ConstantesRestApi;
import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.deserializador.MascotaDeserializador;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p> The RestApiAdapter class</p>
 * <p> Nuevatel PCS de Bolivia S.A. (c) 2016.</p>
 * <p>
 * <p>El contenido de este archivo esta clasificado como: </p>
 * <p> INFORMACION DE CONFIDENCIALIDAD ALTA </p>
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagran(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson contruyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public EndpointsApi establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.SERVER_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(EndpointsApi.class);
    }
}
