package com.coursera.app.pm.mascotitas.restApi;

/**
 * <p> The ConstantesRestApi class</p>
 * <p> Nuevatel PCS de Bolivia S.A. (c) 2016.</p>
 * <p>
 * <p>El contenido de este archivo esta clasificado como: </p>
 * <p> INFORMACION DE CONFIDENCIALIDAD ALTA </p>
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class ConstantesRestApi {

    public static  final String VERSION = "/v1/" ;
    public static final String URL_BASE = "https://api.instagram.com"+VERSION;
    public static final String ACCESS_TOKEN = "3584052004.dfd2a34.653c10699fc84d15946951ec6fd515dd";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";

    //Obtine la informacion de un usuario
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}
