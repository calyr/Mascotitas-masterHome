package com.coursera.app.pm.mascotitas.restApi;

/**
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class ConstantesRestApi {

    public static final String VERSION                      = "/v1/" ;
    public static final String URL_BASE                     = "https://api.instagram.com"+VERSION;
    public static final String ACCESS_TOKEN                 = "3584052004.dfd2a34.653c10699fc84d15946951ec6fd515dd";
    public static final String ACCESS_TOKEN_TEST            = "3440197046.f096016.6e701e3ec983405bbd6bfebf9d665a51";
    public static final String KEY_ACCESS_TOKEN             = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER    = "users/self/media/recent/";
    public static final String KEY_GET_RECENT_MEDIA_USER_ID    = "users/{user-id}/media/recent/";

    public final static String USER_ID_MEDIA_RECENT = "users/{user-id}/";

    //Obtiene la informacion de un usuario
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN

    public static final String URL_GET_RECENT_MEDIA_USER_TEST = KEY_GET_RECENT_MEDIA_USER_ID + KEY_ACCESS_TOKEN + ACCESS_TOKEN_TEST;
    //Consulta my server HEROKU
    public static final String SERVER_URL_BASE              = "https://mighty-forest-19952.herokuapp.com/";
    public static final String SERVER_POST_TOKEN            = "registrar-usuario";
    public static final String KEY_TOQUE_ANIMAL             = "toque-animal/{id}/{animal}";


    //https://api.instagram.com/v1/users/3505167991/relationship?access_token=3440197046.f096016.6e701e3ec983405bbd6bfebf9d665a51
    public final static String KEY_SET_FOLLOW = "users/{user-id}/relationship?access_token="+ACCESS_TOKEN_TEST ;
    public final static String KEY_GET_FOLLOWS              = "follows";

    // https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN
    public final static String KEY_GET_PROFILE_USER = "users/self/";

    //Obtengo los seguidores
    // https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN
    // https://api.instagram.com/v1/users/self/follows?access_token=3440197046.f096016.6e701e3ec983405bbd6bfebf9d665a51
    public final static String URL_GET_FOLLOWS = KEY_GET_PROFILE_USER + KEY_GET_FOLLOWS + KEY_ACCESS_TOKEN +ACCESS_TOKEN_TEST;

    //Obtener los medios recientes por id de usuario
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    public static final String KEY_GET_USERS= "users/{user-id}/media/recent/";
    public final static String URL_GET_FOLOWS_MEDIA_RECENT = KEY_GET_USERS +KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //  https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN
    public final static String URL_GET_PROFILE_USER_WITH_ID = USER_ID_MEDIA_RECENT + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // Likes media
    //https://api.instagram.com/v1/media/1394325059561543348_3584052004/likes
    public final static String LIKES_URL_MEDIA = "media/{media_id}/likes"+ KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //
    public final static String SEND_NOTIFICACION = "send-notification/{token}/{usuario_instagram}/{foto_id}";
}
