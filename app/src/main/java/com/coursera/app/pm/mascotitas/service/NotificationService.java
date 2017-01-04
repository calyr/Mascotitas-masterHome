package com.coursera.app.pm.mascotitas.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.util.Log;
import android.view.Gravity;

import com.coursera.app.pm.mascotitas.MainActivity;
import com.coursera.app.pm.mascotitas.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "NotificationService";
    public static final int NOTIFICATION_ID =    001;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From" + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body" + remoteMessage.getNotification().getBody());

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        //NOFICACION FOLLOW
        Intent iFollow = new Intent();
        iFollow.setAction("FOLLOW");
        PendingIntent pendingIntentFollow = PendingIntent.getBroadcast(this, 0, iFollow, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v7.app.NotificationCompat.Action actionFollow =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_follow,getString(R.string.texto_accion_toque_follow),pendingIntentFollow)
                        .build();
        //NOFICACION FOLLOW
        Intent iUnfollow = new Intent();
        iUnfollow.setAction("UNFOLLOW");
        PendingIntent pendingIntentUnfollow = PendingIntent.getBroadcast(this, 0, iUnfollow, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v7.app.NotificationCompat.Action actionUnfollow =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_unfollow,getString(R.string.texto_accion_toque_unfollow),pendingIntentUnfollow)
                        .build();

        //NOTIFICACION PERFIL

        Intent iPerfil = new Intent();
        iPerfil.setAction("PERFIL");
        PendingIntent pendingIntentPerfil = PendingIntent.getBroadcast(this, 0, iPerfil, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v7.app.NotificationCompat.Action actionPerfil =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_perfil,getString(R.string.texto_accion_toque_perfil),pendingIntentPerfil)
                        .build();

        //NOTIFICACION HOME

        Intent iHome = new Intent();
        iHome.setAction("HOME");

        PendingIntent pendingIntentHome = PendingIntent.getBroadcast(this, 0, iHome, PendingIntent.FLAG_UPDATE_CURRENT);
        android.support.v7.app.NotificationCompat.Action actionHome =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_home,getString(R.string.texto_accion_toque_home),pendingIntentHome)
                        .build();


        android.support.v7.app.NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.backgroundwear))
                        .setGravity(Gravity.CENTER_VERTICAL);




        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bone)
                .setContentTitle("Notificación")
                .setContentText("Contacto Multiples Acciones Notification Firebase")
                .setSound(sonido)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(actionFollow).addAction(actionUnfollow).addAction(actionHome).addAction(actionPerfil))


                ;

        NotificationManagerCompat notificationManager = (NotificationManagerCompat.from(this)) ;
        notificationManager.notify(0, notification.build());


    }
}
