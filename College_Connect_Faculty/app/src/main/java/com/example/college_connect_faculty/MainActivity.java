package com.example.college_connect_faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button uploadSubject,uploadMaterial,circularBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadSubject = findViewById(R.id.uploaSubjectdBtn);
        uploadMaterial = findViewById(R.id.uploadMaterialBtn);
        circularBtn = findViewById(R.id.circularBtn);

        uploadSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,subjectUpload.class);
                startActivity(i);
            }
        });
        uploadMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MainActivity.this,MaterialUpload.class);
                startActivity(i1);
            }
        });
        circularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this,circularNotice.class);
                startActivity(i2);
            }
        });
    }
}