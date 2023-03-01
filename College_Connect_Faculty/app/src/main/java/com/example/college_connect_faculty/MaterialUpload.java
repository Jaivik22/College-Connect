package com.example.college_connect_faculty;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialUpload extends AppCompatActivity {
    Spinner branchSpinner,semSpinner,subjectSpinner;
    String subject;
    String branch;
    String semester;
    ArrayList<Subject> arrayListSubject;
    ImageView chooseFile;
    TextView filenametxt;
    Button uploadfileBtn;
    private static final int PICKFILE_REQUEST_CODE = 8778;
    String uridata;
    String filepath,fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_upload);

        branchSpinner = findViewById(R.id.spinner11);
        semSpinner = findViewById(R.id.spinner22);
        subjectSpinner = findViewById(R.id.spinner33);
        chooseFile = findViewById(R.id.chooseImg);
        filenametxt = findViewById(R.id.filenametxt);
        uploadfileBtn = findViewById(R.id.uploadfileBtn);

        String[] branches = {"Computer Engineering","Information Technology","Computer Science And Engineering","Civil Engineering","Mechanical Engineering"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, branches);
        branchSpinner.setAdapter(adapter);

        String[] semesterNotes = {"Sem1","Sem2","Sem3","Sem4","Sem5","Sem6","Sem7","Sem8"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,semesterNotes);
        semSpinner.setAdapter(adapter1);
        fetchSubjects(branch,semester);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MaterialUpload.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},110);
        }

        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                branch =adapterView.getItemAtPosition(position).toString();
                fetchSubjects(branch,semester);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                branch =adapterView.getItemAtPosition(0).toString();
                fetchSubjects(branch,semester);
            }
        });


        semSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                semester = adapterView.getItemAtPosition(pos).toString();
                fetchSubjects(branch,semester);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                semester = adapterView.getItemAtPosition(0).toString();
                fetchSubjects(branch,semester);
            }
        });
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                subject = adapterView.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                subject = adapterView.getItemAtPosition(0).toString();
            }
        });

        chooseFile.setOnClickListener(new View.OnClickListener() {
            private static final int PICKFILE_REQUEST_CODE = 8778;

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("*/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,100);

            }
        });

        uploadfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!semester.isEmpty() && !subject.isEmpty() && !branch.isEmpty() && !filepath.isEmpty()){
                    RequestBody requestsem = RequestBody.create(MediaType.parse("multipart/form-data"),semester);
                    RequestBody requestbranch = RequestBody.create(MediaType.parse("multipart/form-data"),branch);
                    RequestBody requestsubject = RequestBody.create(MediaType.parse("multipart/form-data"),subject);

                    Map<String, RequestBody> map = new HashMap<>();
                    File file = new File(filepath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                    MultipartBody.Part fileupload = MultipartBody.Part.createFormData("fileName",fileName,requestBody);
                    Call<ResponseBody> call = RetrofitClient.getInstance().getApi().fileUpload(fileupload,requestbranch,requestsem,requestsubject);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String s = response.body().string();
                                if(s.equals("upload suceesful...")){
                                    Toast.makeText(MaterialUpload.this,s,Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MaterialUpload.this,s,Toast.LENGTH_LONG).show();
                                    Log.e("Error",s);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            Context context = MaterialUpload.this;
            filepath = RealPathUtil.getRealPath(context,uri);
            fileName = filepath.substring(filepath.lastIndexOf("/")+1);
            Log.e("Path",filepath);
            filenametxt.setText(fileName);
        }

    }

    public void fetchSubjects(String branch, String semester){
         Call<ResultSubject> call = RetrofitClient.getInstance().getApi().showSubject(semester,branch);
        call.enqueue(new Callback<ResultSubject>() {
            @Override
            public void onResponse(retrofit2.Call<ResultSubject> call, Response<ResultSubject> response) {
                arrayListSubject = (ArrayList<Subject>) response.body().getSubjects();
                String[] subArr = new String[arrayListSubject.size()];
                for (int i=0;i<arrayListSubject.size();i++){
                    String s =arrayListSubject.get(i).getSubject();
                    subArr[i] = s;
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MaterialUpload.this,android.R.layout.simple_spinner_dropdown_item, subArr);
                subjectSpinner.setAdapter(adapter1);


            }

            @Override
            public void onFailure(Call<ResultSubject> call, Throwable t) {

            }
        });


    }
}