package com.example.fady.quiz;

import java.util.ArrayList;

/**
 * Created by fady on 2/12/2015.
 */
public class Question_Take_quiz {
   private ArrayList<MCQS> mcqs;
   private String duration;
   private String quiz_title;
   private String quiz_id;

    public ArrayList<MCQS> getMcqs() {
        return mcqs;
    }

    public void setMcqs(ArrayList<MCQS> mcqs) {
        this.mcqs = mcqs;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }
}
