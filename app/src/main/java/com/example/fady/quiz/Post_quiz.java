package com.example.fady.quiz;

import java.util.ArrayList;

/**
 * Created by fady on 2/13/2015.
 */
public class Post_quiz {
    private String auth_token;
    private String quiz_id;
    private ArrayList<Integer> answers=new ArrayList<>();

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }
}
