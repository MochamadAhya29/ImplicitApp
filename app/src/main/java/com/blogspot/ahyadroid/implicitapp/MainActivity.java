package com.blogspot.ahyadroid.implicitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_audio)
    Button btnAudio;

    @BindView(R.id.btn_notification)
    Button btnNotification;


    @BindView(R.id.btn_wifi)
    Button btnWifi;

    @BindView(R.id.btn_email)
    Button btnEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick ({R.id.btn_audio, R.id.btn_notification, R.id.btn_wifi, R.id.btn_email})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btn_audio:
                startActivity(new Intent(this, Audio.class));
                break;

            case R.id.btn_notification:
                break;

            case R.id.btn_wifi:
                break;

            case R.id.btn_email:
                break;
        }
    }
}
