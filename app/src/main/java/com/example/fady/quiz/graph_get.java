package com.example.fady.quiz;

import java.util.ArrayList;

public class graph_get {

    String seat_number;
    ArrayList<graph> quizzes = new ArrayList<graph>();


    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }


    public ArrayList<graph> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<graph> quizzes) {
        this.quizzes = quizzes;
    }

}
class graph {

    String quiz_title;
    double mark;

    public double getMark() {
        return mark;
    }



    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getQuiz_title() {
        return quiz_title;
    }



    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }


}