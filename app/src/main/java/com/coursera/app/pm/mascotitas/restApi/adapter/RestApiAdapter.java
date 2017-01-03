package com.coursera.app.pm.mascotitas.restApi.adapter;

import com.coursera.app.pm.mascotitas.restApi.ConstantesRestApi;
import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.deserializador.FollowDeserializador;
import com.coursera.app.pm.mascotitas.restApi.deserializador.LikesMediaDeserializador;
import com.coursera.app.pm.mascotitas.restApi.deserializador.MascotaDeserializador;
import com.coursera.app.pm.mascotitas.restApi.deserializador.RelationshipDeserializador;
import com.coursera.app.pm.mascotitas.restApi.deserializador.UsuarioDeserializador;
import com.coursera.app.pm.mascotitas.restApi.model.FollowResponse;
import com.coursera.app.pm.mascotitas.restApi.model.LikesMedia;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.restApi.model.RelationshipResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
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

    public Gson contruyeGsonDeserializadorPerfil(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(MascotaResponse.class, new PerfilDeserializador());
        return gsonBuilder.create();
    }

    public Gson contruyeGsonDeserializadorFoto(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(MascotaResponse.class, new FotoDeserializador());
        return gsonBuilder.create();
    }

    public Gson contruyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson contruyeGsonDeserializadorMediaLikes(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LikesMedia.class, new LikesMediaDeserializador());
        return gsonBuilder.create();
    }

    public Gson contruyeGsonDeserializadorFollow(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FollowResponse.class, new FollowDeserializador());
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

    public Gson contruyeGsonDeserializadorRelationship() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RelationshipResponse.class, new RelationshipDeserializador());
        return gsonBuilder.create();
    }
}
