package com.example.fady.quiz;

import java.util.ArrayList;

/**
 * Created by fady on 2/11/2015.
 */
public class User_Data {

    public String getAuthentication_token() {
        return authentication_token;
    }

    public void setAuthentication_token(String authentication_token) {
        this.authentication_token = authentication_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Subject_Data> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<Subject_Data> subject) {
        this.subject = subject;
    }

    public String getComing_quiz_title() {
        return coming_quiz_title;
    }

    public void setComing_quiz_title(String coming_quiz_title) {
        this.coming_quiz_title = coming_quiz_title;
    }

    public String getComing_quiz_time() {
        return coming_quiz_time;
    }

    public void setComing_quiz_time(String coming_quiz_time) {
        this.coming_quiz_time = coming_quiz_time;
    }

    public String getComing_quiz_id() {
        return coming_quiz_id;
    }

    public void setComing_quiz_id(String coming_quiz_id) {
        this.coming_quiz_id = coming_quiz_id;
    }

    private String authentication_token;
    private String name;
    private String role;
    private ArrayList<Subject_Data> subject;
    private String coming_quiz_title;
    private String coming_quiz_time;
    private String coming_quiz_id;

    public Integer getComing_quiz_duration() {
        return coming_quiz_duration;
    }

    public void setComing_quiz_duration(Integer coming_quiz_duration) {
        this.coming_quiz_duration = coming_quiz_duration;
    }

    private Integer coming_quiz_duration;


}
