package com.coursera.app.pm.mascotitas.restApi;


import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Path;

/**
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_PROFILE_USER_WITH_ID)
    Call<MascotaResponse> getProfile(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_FOLLOWS)
    Call<MascotaResponse> getFollows();

    @GET(ConstantesRestApi.URL_GET_FOLOWS_MEDIA_RECENT)
    Call<MascotaResponse> getFollowsMediaRecent(@Path("user-id") String id);

    @FormUrlEncoded
    @POST(ConstantesRestApi.SERVER_POST_TOKEN)
    Call<UsuarioResponse> registrarTokenId(@Field("token") String token, @Field("userInstagram") String userInstagram);
}
