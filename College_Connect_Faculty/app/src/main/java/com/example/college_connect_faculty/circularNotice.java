package com.example.college_connect_faculty;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class circularNotice extends AppCompatActivity {
    EditText noticetxt,noticeTitle;
    Button noticeBtn;
    TextView stringLength;
    String topic = "com.example.college_connect_faculty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_notice);

        FirebaseMessaging.getInstance().subscribeToTopic("all");
        noticetxt = findViewById(R.id.noticetxt);
        noticeBtn = findViewById(R.id.noticeBtn);
        stringLength = findViewById(R.id.stringLength);
        noticeTitle = findViewById(R.id.noticeTitle);
        noticetxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = noticetxt.getText().toString();
                int l = s.length();
                stringLength.setText(String.valueOf(l));

            }
        });

        noticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(circularNotice.this,"click!!",Toast.LENGTH_SHORT).show();

                    String notice = noticetxt.getText().toString();
                    String title = noticeTitle.getText().toString();
                    try {


                        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().noticeUpload(notice, title);
                        call.enqueue(new Callback<ResponseBody>() {
                            @RequiresApi(api = Build.VERSION_CODES.Q)
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String s = response.body().string();
                                    Toast.makeText(circularNotice.this, s, Toast.LENGTH_SHORT).show();

                                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",
                                            title, notice, getApplicationContext(), circularNotice.this);
                                    notificationsSender.SendNotifications();
                                    FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }catch (Exception e){
                        Toast.makeText(circularNotice.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }





}