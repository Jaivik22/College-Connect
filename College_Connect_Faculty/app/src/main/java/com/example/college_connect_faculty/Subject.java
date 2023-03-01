package com.example.college_connect_faculty;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Subject {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Branch")
    @Expose
    private String branch;
    @SerializedName("Semester")
    @Expose
    private String semester;
    @SerializedName("Subject")
    @Expose
    private String subject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
