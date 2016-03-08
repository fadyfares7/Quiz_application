package com.example.fady.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class test extends Activity {
    ListView list_view_ans;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        list_view_ans=(ListView)findViewById(R.id.list_view_ans);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(test.this,android.R.layout.simple_list_item_1,makeQuiz.array_question_save);
        list_view_ans.setAdapter(adapter);


    }}


