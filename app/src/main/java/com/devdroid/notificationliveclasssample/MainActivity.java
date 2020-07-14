package com.devdroid.notificationliveclasssample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    String CHANNEL_ID = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Bitmap bitmap = ((BitmapDrawable) (getResources().getDrawable(R.drawable.capture))).getBitmap();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Custom Channel";
            String description = "Channel for Updates";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            if (manager!=null)
                manager.createNotificationChannel(channel);
        }

        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pi = PendingIntent.getActivity(this, 100, i, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("New Message")
                .setContentText("Message from Raman")
                .setSmallIcon(R.drawable.classroom)
                .setLargeIcon(bitmap)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pi)
                .build();

        // I made some changes

        //BigPictureStyle
        //InboxStyle


        manager.notify(100, notification);

    }
}