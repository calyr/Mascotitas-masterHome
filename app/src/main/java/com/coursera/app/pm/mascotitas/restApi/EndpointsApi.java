package com.coursera.app.pm.mascotitas.restApi;


import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;

/**
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @FormUrlEncoded
    @POST(ConstantesRestApi.SERVER_POST_TOKEN)
    Call<UsuarioResponse> registrarTokenId(@Field("token") String token, @Field("userInstagram") String userInstagram);
}
