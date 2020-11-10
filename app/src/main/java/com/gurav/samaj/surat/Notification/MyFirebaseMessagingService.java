package com.gurav.samaj.surat.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.gurav.samaj.surat.MainActivity;
import com.gurav.samaj.surat.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("TAG", "onMessageReceived: "+remoteMessage.getData());
//
//        int icon = R.drawable.logo;
//        long when = System.currentTimeMillis();
//        Notification notification = new Notification(icon, "Custom Notification", when);
//
//        NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_layout_notification);
//        contentView.setImageViewResource(R.id.image, R.drawable.logo);
//        contentView.setTextViewText(R.id.title, "News Title");
//        contentView.setTextViewText(R.id.text, "News Description");
//        notification.contentView = contentView;
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.contentIntent = contentIntent;
//
//        notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
//        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
//        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
//        notification.defaults |= Notification.DEFAULT_SOUND; // Sound
//
//        mNotificationManager.notify(1, notification);

    }
}
