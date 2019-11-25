package com.blogspot.ahyadroid.implicitapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Audio extends AppCompatActivity {

    @BindView(R.id.btn_ring)
    Button btnRing;

    @BindView(R.id.btn_silent)
    Button btnSilent;

    @BindView(R.id.btn_vibrate)
    Button btnVibrate;

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);


        ButterKnife.bind(this);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

    }
    @OnClick({R.id.btn_ring, R.id.btn_silent, R.id.btn_vibrate})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btn_ring:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "Mode Normal", Toast.LENGTH_SHORT).show();
                break;


            case R.id.btn_notification:
                showNotification();
                break;

            case R.id.btn_silent:
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()){
                    Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    startActivity(intent);
                }
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "Mode Silent", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_vibrate:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(this, "Mode Vibrate", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showNotification() {
        int NOTIFICATION_ID = 1;
        String CHANNEL_ID = "channel_1";
        String CHANNEL_NAME = "notif channel";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://idn.sch.id"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, intent,0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Simply Notification")
                .setContentText("test Notification")
                .setSubText("Sub text")
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            if (notificationManager != null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification notification = notificationBuilder.build();

        if (notificationManager != null){
                notificationManager.notify(NOTIFICATION_ID, notification);
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "Click Notification", Toast.LENGTH_SHORT).show();
                Log.d("requestCode", "OK");
            }
        }
    }
}




