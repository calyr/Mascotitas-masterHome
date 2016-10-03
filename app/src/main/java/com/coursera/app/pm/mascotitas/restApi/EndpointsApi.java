package com.coursera.app.pm.mascotitas.restApi;


import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * <p> The EndpointsApi class</p>
 * <p> Nuevatel PCS de Bolivia S.A. (c) 2016.</p>
 * <p>
 * <p>El contenido de este archivo esta clasificado como: </p>
 * <p> INFORMACION DE CONFIDENCIALIDAD ALTA </p>
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

}
