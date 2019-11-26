package com.blogspot.ahyadroid.implicitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Sms extends AppCompatActivity {

    @BindView(R.id.to)
    EditText edtTo;

    @BindView(R.id.edt_body_sms)
    EditText edtBodySms;


    @BindView(R.id.btn_send_sms)
    Button btnSendSms;

    @BindView(R.id.btn_send_intent)
    Button btnSendIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                        10);
            }
        }
    }


    @OnClick({R.id.to,R.id.edt_body_sms, R.id.btn_send_sms, R.id.btn_send_intent})
    public void onViewClicked(View view){
        String noTelp = edtTo.getText().toString().trim();
        String bodySms = edtBodySms.getText().toString().trim();

        switch (view.getId()){
            case R.id.to:
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 100);
                break;

            case R.id.btn_send:
                if (TextUtils.isEmpty(noTelp) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "Fill Required", Toast.LENGTH_SHORT).show();
                } else {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(noTelp, null, bodySms, null, null);
                    Toast.makeText(this, "Sent", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_send_intent:
                if (TextUtils.isEmpty(noTelp) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "Fill Required", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentsms = new Intent(Intent.ACTION_SEND);
                    intentsms.setData(Uri.parse("sms to:" + Uri.encode(noTelp)));
                    intentsms.putExtra("sms_body", bodySms);
                    startActivity(intentsms);
                }
                break;

        }
    }


}
