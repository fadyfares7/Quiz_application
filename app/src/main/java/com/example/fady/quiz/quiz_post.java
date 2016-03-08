package com.example.fady.quiz;

import java.util.ArrayList;

/**
 * Created by Toshiba on 11/02/2015.
 */
public class quiz_post {
    private String quiz_title;
    private String time_to_be_published;
    private String duration;
    private String course_code;
    private ArrayList<quiz_content> mcqs=new ArrayList<quiz_content>();
    private String auth_token;

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getTime_to_be_published() {
        return time_to_be_published;
    }

    public void setTime_to_be_published(String time_to_be_published) {
        this.time_to_be_published = time_to_be_published;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public ArrayList<quiz_content> getMcqs() {
        return mcqs;
    }

    public void setMcqs(ArrayList<quiz_content> mcqs) {
        this.mcqs = mcqs;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
}
class quiz_content {

    private String question;

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int question_mark;
    private int answer;


    public void setAnswer(int answer) {
        this.answer = answer;
    }


    public void setQuestion_mark(int question_mark) {
        this.question_mark = question_mark;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public int getQuestion_mark() {
        return question_mark;
    }

    public int getAnswer() {
        return answer;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }


}
