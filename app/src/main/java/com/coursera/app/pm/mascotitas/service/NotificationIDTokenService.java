package com.coursera.app.pm.mascotitas.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * <p> The NotificationIDTokenService class</p>
 * <p> Nuevatel PCS de Bolivia S.A. (c) 2016.</p>
 * <p/>
 * <p>El contenido de este archivo esta clasificado como: </p>
 * <p> INFORMACION DE CONFIDENCIALIDAD ALTA </p>
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class NotificationIDTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "firebase token";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println(token);

    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG,token);
    }
}
