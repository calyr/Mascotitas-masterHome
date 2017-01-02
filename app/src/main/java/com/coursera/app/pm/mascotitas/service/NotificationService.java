package com.coursera.app.pm.mascotitas.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.util.Log;

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



        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bone)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_full_dog,getString(R.string.texto_accion_toque), pendingIntent)
        ;

        NotificationManagerCompat notificationManager = (NotificationManagerCompat.from(this)) ;

        notificationManager.notify(NOTIFICATION_ID, notification.build());


    }
}
