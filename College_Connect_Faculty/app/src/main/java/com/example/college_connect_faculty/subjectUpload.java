package com.example.college_connect_faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class subjectUpload extends AppCompatActivity {
    Spinner dropdownbranch,dropdownsem;
    EditText subjecttxt;
    Button submitBtn;
    String subject,branch,semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_upload);

        dropdownbranch = findViewById(R.id.spinner1);
        dropdownsem = findViewById(R.id.spinner2);
        subjecttxt = findViewById(R.id.enterSubject);
        submitBtn = findViewById(R.id.subjectBtn);


        String[] branches = {"Computer Engineering","Information Technology","Computer Science And Engineering","Civil Engineering","Mechanical Engineering"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, branches);
        dropdownbranch.setAdapter(adapter);

        String[] semesterNotes = {"Sem1","Sem2","Sem3","Sem4","Sem5","Sem6","Sem7","Sem8"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,semesterNotes);
        dropdownsem.setAdapter(adapter1);

        dropdownbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                branch =adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                branch =adapterView.getItemAtPosition(0).toString();
            }
        });


        dropdownsem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                semester = adapterView.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                semester = adapterView.getItemAtPosition(0).toString();
            }

        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject = subjecttxt.getText().toString();
                Call<ResponseBody> call =RetrofitClient.getInstance().getApi().uploadSubject(semester,branch,subject);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String s = response.body().string();
                            if (s.equals("Upload Successful")){
                                Toast.makeText(subjectUpload.this,s,Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(subjectUpload.this,MainActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(subjectUpload.this,s,Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            Toast.makeText(subjectUpload.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });



    }
}