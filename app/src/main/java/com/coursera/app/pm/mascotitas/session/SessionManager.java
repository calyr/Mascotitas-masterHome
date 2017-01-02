package com.coursera.app.pm.mascotitas.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.coursera.app.pm.mascotitas.ConfigurarCuentaActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "courseraPetagrama";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";

    private static final String USER_PERFIL = "USERNAME" ;
    private static final String USER_PERFIL_ID = "USERNAMEID" ;
    private static final String USER_PERFIL_PHOTO = "USERNAMEPHOTO" ;

    public SessionManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }




    public void createSession(String account,String fullName, String id, String photo){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, account);
        editor.putString(USER_PERFIL, fullName);
        editor.putString(USER_PERFIL_ID, id);
        editor.putString(USER_PERFIL_PHOTO, photo);
        editor.commit();
    }

    public HashMap<String, String> getUser(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(USER_PERFIL, pref.getString(USER_PERFIL, null));
        user.put(USER_PERFIL_ID, pref.getString(USER_PERFIL_ID, null));
        user.put(USER_PERFIL_PHOTO, pref.getString(USER_PERFIL_PHOTO, null));
        return user;
    }

    public void checklogin(){
        if( !this.isLoggedIn()) {
            Intent i = new Intent(_context, ConfigurarCuentaActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN,false);
    }
}
