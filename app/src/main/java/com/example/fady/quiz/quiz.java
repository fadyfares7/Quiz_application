package com.example.fady.quiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")


public class quiz extends ActionBarActivity {
    private TextView text_view_question;

    private Button button_next;
    private Button button_back;
    private Button button_finish;

    private RadioButton radio_button_ans1;
    private RadioButton radio_button_ans2;
    private RadioButton radio_button_ans3;
    private RadioButton radio_button_ans4;
    private RadioGroup radio_group_answers;
    private RadioButton radio_button_selected_item;

    public static ArrayList<String> array_question_save = new ArrayList<String>();
    public static ArrayList<String> array_ans1_save =new ArrayList<String>();
    public static ArrayList<String> array_ans2_save = new ArrayList<String>();
    public static ArrayList<String> array_ans3_save = new ArrayList<String>();
    public static ArrayList<String> array_ans4_save = new ArrayList<String>();
    public static ArrayList<Integer> array_correct_answer_save = new ArrayList<Integer>();
    public static ArrayList<Integer> temp = new ArrayList<>();
    private View button_back_view;
    private View button_finish_view;
    private View button_next_view;
    private TextView textViewTime;
    private TextView textView_quizTitle;
    public static Post_quiz post_quiz;
    public static Grade mark;
    public ArrayList<MCQS> setarrayData;

    int i = 0;
    int j=0;
    int finish=0;
    int dur;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takequiz_quiz);
        text_view_question = (TextView) findViewById(R.id.text_view_question);
        textView_quizTitle=(TextView) findViewById(R.id.textView_quizTitle);
        button_next = (Button) findViewById(R.id.button_next);
        button_back = (Button) findViewById(R.id.button_back);
        button_finish = (Button) findViewById(R.id.button_finish);
        button_back_view=findViewById(R.id.button_back);
        button_finish_view=findViewById(R.id.button_finish);
        button_next_view=findViewById(R.id.button_next);
        mark=new Grade();


        radio_button_ans1 = (RadioButton) findViewById(R.id.radio_button_ans1);
        radio_button_ans2 = (RadioButton) findViewById(R.id.radio_button_ans2);
        radio_button_ans3 = (RadioButton) findViewById(R.id.radio_button_ans3);
        radio_button_ans4 = (RadioButton) findViewById(R.id.radio_button_ans4);
        radio_group_answers = (RadioGroup) findViewById(R.id.radio_group_answers);
        textViewTime=(TextView)findViewById(R.id.textViewTime);
        post_quiz=new Post_quiz();
        setarrayData=student.quizData.getMcqs();
        textView_quizTitle.setText(student.quizData.getQuiz_title());

        button_finish_view.setVisibility(View.INVISIBLE);

        //dummy questions
        setarray();
      //initialize();


        if(i==0){
            button_back_view.setVisibility(View.INVISIBLE);
        }
        text_view_question.setText(array_question_save.get(0));
        radio_button_ans1.setText(array_ans1_save.get(0));
        radio_button_ans2.setText(array_ans2_save.get(0));
        radio_button_ans3.setText(array_ans3_save.get(0));
        radio_button_ans4.setText(array_ans4_save.get(0));





       dur= Login.user.getComing_quiz_duration();
        final CounterClass timer = new CounterClass(dur*60*1000,1000);


        // TODO Auto-generated method stub
        timer.start();



    }
    public void setarray()
    {
        for(int m=0;m<setarrayData.size();m++){
            array_question_save.add(m,setarrayData.get(m).getQuestion());
        }
        for(int m=0;m<setarrayData.size();m++){
            array_ans1_save.add(m,setarrayData.get(m).getChoice1());
        }
        for(int m=0;m<setarrayData.size();m++){
            array_ans2_save.add(m,setarrayData.get(m).getChoice2());
        }
        for(int m=0;m<setarrayData.size();m++){
            array_ans3_save.add(m,setarrayData.get(m).getChoice3());
        }
        for(int m=0;m<setarrayData.size();m++){
            array_ans4_save.add(m,setarrayData.get(m).getChoice4());
        }

    }

    /*public void initialize()
    {

        array_question_save.add(0, "Q1) ADD(1,1)");
        array_ans1_save.add(0, "2");
        array_ans2_save.add(0, "3");
        array_ans3_save.add(0, "1");
        array_ans4_save.add(0, "0");


        array_question_save.add(1, "Q2) Mul(2,2)");
        array_ans1_save.add(1, "3");
        array_ans2_save.add(1, "4");
        array_ans3_save.add(1, "5");
        array_ans4_save.add(1, "1");


        array_question_save.add(2, "div(4,2)");
        array_ans1_save.add(2, "2");
        array_ans2_save.add(2, "1");
        array_ans3_save.add(2, "0");
        array_ans4_save.add(2, "8");
    }
    */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            textViewTime.setText("Completed.");
            int ans=0;

            if (radio_button_ans1.isChecked())
                ans=1;
            else if (radio_button_ans2.isChecked())
                ans=2;
            else if (radio_button_ans3.isChecked())
                ans=3;
            else if (radio_button_ans4.isChecked())
                ans=4;

            if(array_question_save.contains(i))
                array_correct_answer_save.set(i,ans);


            else
                array_correct_answer_save.add(i,ans);
            if(finish==0) {
                array_question_save.clear();
                array_ans1_save.clear();
                array_ans2_save.clear();
                array_ans3_save.clear();
                array_ans4_save.clear();
                send_quiz();

               // Intent intent_make_quiz = new Intent(quiz.this, finish.class);
               //finish();
               // startActivityForResult(intent_make_quiz, 0);
            }

        }}



    public  void finish(View view)
    {

        if(radio_button_ans1.isChecked()==false&&radio_button_ans2.isChecked()==false&&radio_button_ans3.isChecked()==false&&radio_button_ans4.isChecked()==false){
            Toast.makeText(quiz.this, "You must choose an answer ", Toast.LENGTH_SHORT).show();}
        else{

            array_question_save.clear();
            array_ans1_save.clear();
            array_ans2_save.clear();
            array_ans3_save.clear();
            array_ans4_save.clear();
            onRadioButtonClicked(view);
            finish=1;
            send_quiz();


            //finish();

        }}
    public void send_quiz()
    {
        post_quiz.setAuth_token(Login.Token);
        post_quiz.setQuiz_id(student.quizData.getQuiz_id());
        post_quiz.setAnswers(array_correct_answer_save);
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Login.ENDPOINT)
                .build();
        RequestAPI api2 = adapter.create(RequestAPI.class);
        api2.post_quiz(post_quiz,new Callback<Grade>() {
            @Override
            public void success(Grade grade, Response response) {
                mark=grade;
                DialogFragment newFragment = new ShowGrade();
                newFragment.show(getFragmentManager(), "ShowGrade");

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(quiz.this, "Quiz sent failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void back(View view)
    {
        button_finish_view.setVisibility(View.INVISIBLE);
        if (radio_button_ans1.isChecked())
            temp.add(j, 1);
        else if (radio_button_ans2.isChecked())
            temp.add(j, 2);
        else if (radio_button_ans3.isChecked())
            temp.add(j, 3);
        else if (radio_button_ans4.isChecked())
            temp.add(j, 4);
        j++;

        i--;
        if(i==0){
            button_back_view.setVisibility(View.INVISIBLE);
            button_next_view.setVisibility(View.VISIBLE);

            text_view_question.setText(array_question_save.get(i));
            radio_button_ans1.setText(array_ans1_save.get(i));
            radio_button_ans2.setText(array_ans2_save.get(i));
            radio_button_ans3.setText(array_ans3_save.get(i));
            radio_button_ans4.setText(array_ans4_save.get(i));



            if(array_correct_answer_save.get(i)==1)
                radio_button_ans1.setChecked(true);
            else if (array_correct_answer_save.get(i)==2)
                radio_button_ans2.setChecked(true);

            else if (array_correct_answer_save.get(i)==3)
                radio_button_ans3.setChecked(true);
            else if (array_correct_answer_save.get(i)==4)
                radio_button_ans4.setChecked(true);
            array_correct_answer_save.remove(i);




        }


        else{
            button_next_view.setVisibility(View.VISIBLE);
            text_view_question.setText(array_question_save.get(i));

            radio_button_ans1.setText(array_ans1_save.get(i));
            radio_button_ans2.setText(array_ans2_save.get(i));
            radio_button_ans3.setText(array_ans3_save.get(i));
            radio_button_ans4.setText(array_ans4_save.get(i));



            if(array_correct_answer_save.get(i)==1)
                radio_button_ans1.setChecked(true);
            else if (array_correct_answer_save.get(i)==2)
                radio_button_ans2.setChecked(true);
            else if (array_correct_answer_save.get(i)==3)
                radio_button_ans3.setChecked(true);
            else if (array_correct_answer_save.get(i)==4)
                radio_button_ans4.setChecked(true);
            array_correct_answer_save.remove(i);


        }

    }


    public void next(View view) {


        if (radio_button_ans1.isChecked() == false && radio_button_ans2.isChecked() == false && radio_button_ans3.isChecked() == false && radio_button_ans4.isChecked() == false) {
        Toast.makeText(quiz.this, "You must choose an answer ", Toast.LENGTH_SHORT).show();

    } else {

        if (i == (array_question_save.size() - 2)) {
            onRadioButtonClicked(view);

            button_next_view.setVisibility(View.INVISIBLE);
            button_finish_view.setVisibility(View.VISIBLE);

            i++;

            text_view_question.setText(array_question_save.get(i));
            radio_button_ans1.setText(array_ans1_save.get(i));
            radio_button_ans2.setText(array_ans2_save.get(i));
            radio_button_ans3.setText(array_ans3_save.get(i));
            radio_button_ans4.setText(array_ans4_save.get(i));



        } else {
            onRadioButtonClicked(view);

            button_back_view.setVisibility(View.VISIBLE);


            i++;

            text_view_question.setText(array_question_save.get(i));
            radio_button_ans1.setText(array_ans1_save.get(i));
            radio_button_ans2.setText(array_ans2_save.get(i));
            radio_button_ans3.setText(array_ans3_save.get(i));
            radio_button_ans4.setText(array_ans4_save.get(i));

        }
    }
    if(j==0){
        radio_group_answers.clearCheck();
    }
    else{

        j--;
        if(temp.get(j)==1)
            radio_button_ans1.setChecked(true);
        else if (temp.get(j)==2)
            radio_button_ans2.setChecked(true);
        else if(temp.get(j)==3)
            radio_button_ans3.setChecked(true);
        else if(temp.get(j)==4)
            radio_button_ans4.setChecked(true);
    }
}



    public void onRadioButtonClicked(View view) {

        int ans=0;

        if (radio_button_ans1.isChecked())
            ans=1;
        else if (radio_button_ans2.isChecked())
            ans=2;
        else if (radio_button_ans3.isChecked())
            ans=3;
        else if (radio_button_ans4.isChecked())
            ans=4;

        if(array_question_save.contains(i))
            array_correct_answer_save.set(i,ans);


        else
            array_correct_answer_save.add(i,ans);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }
    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(getString(R.string.mail)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);}

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_messages:
                composeEmail();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
        //noinspection SimplifiableIfStatement

    }
}
