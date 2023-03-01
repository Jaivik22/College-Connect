package com.example.college_connect_faculty;



import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResultSubject {

    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects = null;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
