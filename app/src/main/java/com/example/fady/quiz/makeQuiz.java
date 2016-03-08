package com.example.fady.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Reem on 2/10/2015.
 */
public class makeQuiz extends Activity {

    private Button button_add_another_question ;
    private Button button_finish;
    private Button button_back;

    private EditText text_field_question;
    private EditText text_field_ans1;
    private EditText text_field_ans2;
    private EditText text_field_ans3;
    private EditText text_field_ans4;


    private TextView text_view_question;
    private TextView text_view_answer;
    private TextView text_view_correct_answer;

    public static ArrayList<String> array_question_save=new ArrayList<String>();
    public static ArrayList<String> array_ans1_save=new ArrayList <String>();
    public static ArrayList<String> array_ans2_save=new ArrayList<String>();
    public static ArrayList<String> array_ans3_save=new ArrayList<String>();
    public static ArrayList<String> array_ans4_save=new ArrayList<String>();
    public static ArrayList<Integer> array_selected_item_save=new ArrayList<Integer>();
    private View button_back_view;
    private View button_finish_view;
    private View button_add_another_question_view;

    private Spinner spinner_choices;
    private ListView list_finish;
    public static int i;
    private int j=0;
    String a,b,c,d;


    private ArrayList<String> temp_question = new ArrayList<>();
    private ArrayList<String> temp_ans1 = new ArrayList<>();
    private ArrayList<String> temp_ans2 = new ArrayList<>();
    private ArrayList<String> temp_ans3 = new ArrayList<>();
    private ArrayList<String> temp_ans4 = new ArrayList<>();
    private ArrayList<Integer> temp_correct_ans=new ArrayList<Integer>();
    private ArrayList<Integer> temp_correct_next=new ArrayList<Integer>();

    public static quiz_post quizPost=new quiz_post();
    public static quiz_get quizGet;
    public static ArrayList<quiz_content> qui1=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_quiz_questions);


        button_add_another_question = (Button) findViewById(R.id.button_next);
        text_field_question=(EditText)findViewById(R.id.text_field_question);
        text_field_ans1=(EditText)findViewById(R.id.text_field_ans1);
        text_field_ans2=(EditText)findViewById(R.id.text_field_ans2);
        text_field_ans3=(EditText)findViewById(R.id.text_field_ans3);
        text_field_ans4=(EditText)findViewById(R.id.text_field_ans4);
        text_view_question=(TextView)findViewById(R.id.text_view_question);
        text_view_answer=(TextView)findViewById(R.id.text_view_answer);

        text_view_correct_answer=(TextView)findViewById(R.id.text_view_correct_answer);

        spinner_choices=(Spinner)findViewById(R.id.spinner_choices);
        button_finish=(Button)findViewById(R.id.button_finish);
        button_back=(Button)findViewById(R.id.button_back);
        button_back_view=findViewById(R.id.button_back);
        button_finish_view=findViewById(R.id.button_finish);
        button_add_another_question_view=findViewById(R.id.button_next);



        a=text_field_ans1.getText().toString();
        b=text_field_ans2.getText().toString();
        c=text_field_ans3.getText().toString();
        d=text_field_ans4.getText().toString();
        addItemsOnSpinner();
        if(i==0)
        {
            button_back_view.setVisibility(View.INVISIBLE);
            //button_finish_view.setVisibility(View.GONE);
        }



        button_add_another_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button_back_view.setVisibility(View.VISIBLE);
                //button_finish_view.setVisibility(View.VISIBLE);

                if(array_question_save.contains(i))
                {
                    array_selected_item_save.set(i, (Integer) spinner_choices.getSelectedItem());
                    array_question_save.set(i, text_field_question.getText().toString());
                    array_ans1_save.set(i, text_field_ans1.getText().toString());
                    array_ans2_save.set(i, text_field_ans2.getText().toString());
                    array_ans3_save.set(i, text_field_ans3.getText().toString());
                    array_ans4_save.set(i, text_field_ans4.getText().toString());
                    temp_correct_next.set(i, spinner_choices.getSelectedItemPosition());
                }

                else {

                    array_selected_item_save.add(i, (Integer) spinner_choices.getSelectedItem());
                    array_question_save.add(i, text_field_question.getText().toString());
                    array_ans1_save.add(i, text_field_ans1.getText().toString());
                    array_ans2_save.add(i, text_field_ans2.getText().toString());
                    array_ans3_save.add(i, text_field_ans3.getText().toString());
                    array_ans4_save.add(i, text_field_ans4.getText().toString());
                    temp_correct_next.add(i, spinner_choices.getSelectedItemPosition());
                }

                if (
                        array_question_save.contains(text_field_question.getText().toString()) &&
                                array_ans1_save.contains(text_field_ans1.getText().toString()) && array_ans2_save.contains(text_field_ans2.getText().toString())
                                && array_ans3_save.contains(text_field_ans3.getText().toString()) && array_ans4_save.contains(text_field_ans4.getText().toString()) )
                    Toast.makeText(getBaseContext(), "item added", Toast.LENGTH_SHORT).show();



                i++;


                if(j==0) {

                    text_field_question.setText(" ");
                    text_field_ans1.setText(" ");
                    text_field_ans2.setText(" ");
                    text_field_ans3.setText(" ");
                    text_field_ans4.setText(" ");

                    spinner_choices.setSelection(0);
                }
                else{
                    j--;
                    text_field_question.setText(temp_question.get(j));
                    text_field_ans1.setText(temp_ans1.get(j));
                    text_field_ans2.setText(temp_ans2.get(j));
                    text_field_ans3.setText(temp_ans3.get(j));
                    text_field_ans4.setText(temp_ans4.get(j));
                    spinner_choices.setSelection(temp_correct_ans.get(j));
                }

            }



        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp_question.add(j,text_field_question.getText().toString());
                temp_ans1.add(j,text_field_ans1.getText().toString());
                temp_ans2.add(j,text_field_ans2.getText().toString());
                temp_ans3.add(j,text_field_ans3.getText().toString());
                temp_ans4.add(j,text_field_ans4.getText().toString());
                temp_correct_ans.add(j,spinner_choices.getSelectedItemPosition());
                j++;


                i--;
                if (i == 0) {
                    button_back_view.setVisibility(View.INVISIBLE);
                    button_add_another_question_view.setVisibility(View.VISIBLE);
                    text_field_question.setText(array_question_save.get(i));
                    text_field_ans1.setText(array_ans1_save.get(i));
                    text_field_ans2.setText(array_ans2_save.get(i));
                    text_field_ans3.setText(array_ans3_save.get(i));
                    text_field_ans4.setText(array_ans4_save.get(i));
                    spinner_choices.setSelection(temp_correct_next.get(i));
                     array_question_save.remove(i);

                } else {
                    button_add_another_question_view.setVisibility(View.VISIBLE);
                    text_field_question.setText(array_question_save.get(i));
                    text_field_ans1.setText(array_ans1_save.get(i));
                    text_field_ans2.setText(array_ans2_save.get(i));
                    text_field_ans3.setText(array_ans3_save.get(i));
                    text_field_ans4.setText(array_ans4_save.get(i));
                    spinner_choices.setSelection(temp_correct_next.get(i));
                    array_question_save.remove(i);
                }
            }
        });

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   if(array_question_save.contains(i))
                {
                    array_selected_item_save.set(i, (Integer) spinner_choices.getSelectedItem());
                    array_question_save.set(i, text_field_question.getText().toString());
                    array_ans1_save.set(i, text_field_ans1.getText().toString());
                    array_ans2_save.set(i, text_field_ans2.getText().toString());
                    array_ans3_save.set(i, text_field_ans3.getText().toString());
                    array_ans4_save.set(i, text_field_ans4.getText().toString());
                    temp_correct_next.set(i, spinner_choices.getSelectedItemPosition());
                }

                else { */

                array_selected_item_save.add(i, (Integer) spinner_choices.getSelectedItem());
                array_question_save.add(i, text_field_question.getText().toString());
                array_ans1_save.add(i, text_field_ans1.getText().toString());
                array_ans2_save.add(i, text_field_ans2.getText().toString());
                array_ans3_save.add(i, text_field_ans3.getText().toString());
                array_ans4_save.add(i, text_field_ans4.getText().toString());
                temp_correct_next.add(i, spinner_choices.getSelectedItemPosition());
          //  }
                quizPost.setCourse_code(instructor.quiz_code.getSelectedItem().toString());
                quizPost.setAuth_token(Login.Token);
                quizPost.setDuration(instructor.quiz_duration.getText().toString());
                quizPost.setQuiz_title(instructor.quiz_title.getText().toString());
                quizPost.setTime_to_be_published(instructor.time_date);


                for ( int k = 0; k < array_question_save.size(); k++)
                {

                    quiz_content qui=new quiz_content();
                    qui.setQuestion(array_question_save.get(k));
                    qui.setChoice1(array_ans1_save.get(k));
                    qui.setChoice2(array_ans2_save.get(k));
                    qui.setChoice3(array_ans3_save.get(k));
                    qui.setChoice4(array_ans4_save.get(k));
                    qui.setAnswer(array_selected_item_save.get(k));
                    qui.setQuestion_mark(1);
                    qui1.add(k, qui);


                }
                quizPost.setMcqs(qui1);
                post_instructor(v);

                Intent intent = new Intent(v.getContext(), instructor.class);
                finish();
                startActivityForResult(intent, 0);


                View button_finish_view=findViewById(R.id.button_finish);
                button_finish_view.setVisibility(View.GONE);
            }
        });


    }
   /* protected void onResume()
    {
        addItemsOnSpinner();
    }
    */

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void addItemsOnSpinner(){


        ArrayList<Integer> list = new ArrayList<Integer>();

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_choices.setAdapter(dataAdapter);
        list.add(1);//text_field_ans1.getText().toString());
        list.add(2);//text_field_ans2.getText().toString());
        list.add(3);//text_field_ans3.getText().toString());
        list.add(4);//text_field_ans4.getText().toString());
        dataAdapter.notifyDataSetChanged();

    }
    public void post_instructor(View view)
    {
        RestAdapter adapter=new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Login.ENDPOINT)
                .build();
        RequestAPI api=adapter.create(RequestAPI.class);
        api.post_instructor(quizPost, new Callback<quiz_get>() {
            @Override
            public void success(quiz_get quizGet1, Response response) {
                Toast.makeText(getBaseContext(), "Quiz saved", Toast.LENGTH_LONG).show();
                quizGet = quizGet1;
                array_ans1_save.clear();
                array_ans2_save.clear();
                array_ans3_save.clear();
                array_ans4_save.clear();
                array_question_save.clear();
                //Toast.makeText(getBaseContext(), "success", Toast.LENGTH_LONG).show();


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getBaseContext(), "fail to connect", Toast.LENGTH_LONG).show();

            }

        });}

}





