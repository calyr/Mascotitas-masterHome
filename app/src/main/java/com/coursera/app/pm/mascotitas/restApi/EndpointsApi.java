package com.coursera.app.pm.mascotitas.restApi;


import com.coursera.app.pm.mascotitas.restApi.model.FollowResponse;
import com.coursera.app.pm.mascotitas.restApi.model.LikesMedia;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.restApi.model.RelationshipResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

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

    @GET(ConstantesRestApi.URL_GET_FOLLOWS)
    Call<FollowResponse> getRecentMediaFollow();


    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_TEST)
    Call<MascotaResponse> getRecentMediaTest(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_PROFILE_USER_WITH_ID)
    Call<MascotaResponse> getProfile(@Path("user-id") String id);

    @GET(ConstantesRestApi.URL_GET_FOLLOWS)
    Call<UsuarioResponse> getFollows();

    @GET(ConstantesRestApi.URL_GET_FOLOWS_MEDIA_RECENT)
    Call<MascotaResponse> getFollowsMediaRecent(@Path("user-id") String id);

    @FormUrlEncoded
    @POST(ConstantesRestApi.SERVER_POST_TOKEN)
    Call<UsuarioResponse> registrarTokenId(@Field("token") String token, @Field("userInstagram") String userInstagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_SET_FOLLOW)
    Call<RelationshipResponse> setRelationship(@Path("user-id") String id, @Field("action") String action);

    @POST(ConstantesRestApi.LIKES_URL_MEDIA)
    Call<LikesMedia> likeImagen(@Path("media_id") String mediaId);

    @GET(ConstantesRestApi.SEND_NOTIFICACION)
    Call<UsuarioResponse> sendNotification(@Path("token") String token,@Path("usuario_instagram") String usuario,@Path("foto_id") String foto);
}
