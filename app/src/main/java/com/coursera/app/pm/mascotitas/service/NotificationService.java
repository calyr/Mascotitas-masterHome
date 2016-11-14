package com.coursera.app.pm.mascotitas.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.coursera.app.pm.mascotitas.MainActivity;
import com.coursera.app.pm.mascotitas.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * <p> The NotificationService class</p>
 * <p> Nuevatel PCS de Bolivia S.A. (c) 2016.</p>
 * <p/>
 * <p>El contenido de este archivo esta clasificado como: </p>
 * <p> INFORMACION DE CONFIDENCIALIDAD ALTA </p>
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "NotificationService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From" + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body" + remoteMessage.getNotification().getBody());

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bone)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification.build());


    }
}
