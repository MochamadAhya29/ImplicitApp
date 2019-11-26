package com.blogspot.ahyadroid.implicitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Email extends AppCompatActivity {

    @BindView(R.id.btn_send)
    Button btnSend;

    @BindView(R.id.edt_email)
    Button edtEmail;

    @BindView(R.id.edt_subject)
    Button edtSubject;

    @BindView(R.id.edt_body_email)
    Button edtBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

//        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked(){

        String email = edtEmail.getText().toString().trim();
        String subject = edtSubject.getText().toString().trim();
        String body = edtBody.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(subject) || TextUtils.isEmpty(body)){
            Toast.makeText(this, "Fill Required", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            intent.setType("message/rfc822");
            startActivity(intent);
        }
    }
}
