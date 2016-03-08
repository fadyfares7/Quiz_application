package com.example.fady.quiz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class instructor extends ActionBarActivity implements ActionBar.TabListener {
    public static TextView quiz_title;
    public static TextView quiz_duration;
    public static Spinner quiz_code;
    public static String date;
    public static String time;
    public static int zero=0;
    public static String time_date;
    public static String seat;
    public static User_Data instr;

    //public static String token="mg8Nx5M98x1FoUomv-vB";

    public static ArrayList<String> courseCode=new ArrayList<>();
    public static String course_code;

    public static graph_get graphGet=new graph_get();
    public static ArrayList<graph> graphArray=new ArrayList<>();
    public static TextView seat_number;
    String y;
    double x=2;







    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    private ArrayList<String> z=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        // Set up the action bar.
        instr=Login.user;



       courseCode.add(0,"M1");
       courseCode.add(1,"CSE 321");





        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))

                            .setTabListener(this));
        }
    }

    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(getString(R.string.mail)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below)
            if (position == 0)
                return PlaceholderFragment.newInstance(position);
            else if (position == 1)
                return PlaceholderFragment2.newInstance(position + 1);
            else
                return PlaceholderFragment3.newInstance(position + 2);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.instructor_makequiz).toUpperCase(l);
                case 1:
                    return getString(R.string.instructor_prevquizs).toUpperCase(l);
                case 2:
                    return getString(R.string.instructor_statistics).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        instructor aa=new instructor();





        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.instructor_make_quiz, container, false);

            aa.quiz_title=(TextView)rootView.findViewById(R.id.quiz_name);
            aa.quiz_duration=(TextView)rootView.findViewById(R.id.duration);
            aa.quiz_code=(Spinner)rootView.findViewById(R.id.subjects);




            addItemsOnSpinner();



            return rootView;
        }


        public void addItemsOnSpinner(){

            ArrayList <String> list=new ArrayList<String>();

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                    android.R.layout.simple_spinner_item,list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            quiz_code.setAdapter(dataAdapter);
           /* for(int m=0;m<courseCode.size();m++){
                list.add(m,courseCode.get(m));

            }*/
            for(int m=0;m<courseCode.size();m++){
                list.add(m,courseCode.get(m));}


            dataAdapter.notifyDataSetChanged();


        }
    }


    public void make_quiz(View view) {
        time_date=date+" "+time+":00";
        makeQuiz.i=0;
        makeQuiz.array_question_save.clear();
        makeQuiz.array_ans1_save.clear();
        makeQuiz. array_ans2_save.clear();
        makeQuiz. array_ans3_save.clear();
        makeQuiz. array_ans4_save.clear();
        makeQuiz.array_question_save.clear();
        Intent intent = new Intent(this, makeQuiz.class);
        startActivity(intent);
    }


    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment2 = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment2.setArguments(args);
            return fragment2;
        }

        public PlaceholderFragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.instructor_previousquizs, container, false);
            return rootView;
        }
    }

    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */




        private static final String ARG_SECTION_NUMBER = "section_number";
        private Spinner spinner_subjects;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment3 = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment3.setArguments(args);
            return fragment3;
        }

        public PlaceholderFragment3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.instructor_statistics, container, false);
            seat_number= (TextView) rootView.findViewById(R.id.seat_number);
            return rootView;
        }

    }

    public void line_chart(View view) {
        seat=seat_number.getText().toString();
        get_statistics(view);

        //OpenChart();

    }

    protected void OpenChart(){
        //////////// y=marks of student
        ArrayList<Double>y=new ArrayList<Double>();
        for (int j=0;j<graphArray.size();j++) {
            //y.add(0, 1.0);
            //y.add(1, 2.0);
            //y.add(2, 3.0);
            //y.add(3, 4.0);
           //y.add(4, 5.0);
            graph graph=new graph();
            graph=graphArray.get(j);
            y.add(j,graph.getMark());
        }
        for (int i=0;i<graphArray.size();i++){
            ////z name of quiz
            //z.add(0,"a");
            //z.add(1,"b");
            //z.add(2,"c");
            //z.add(3,"d");
            //z.add(4,"e");
            graph graph =new graph();
            graph=graphArray.get(i);
            z.add(i,graph.getQuiz_title());
        }


        //int []a={2000,4000,5000,7000,2000};
        XYSeries series1=new XYSeries("student1");
        //XYSeries series2=new XYSeries("y");
        for(int i=0;i<graphArray.size();i++){

            series1.add(i,y.get(i));


        }
        XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
        dataset.addSeries(series1);
        //dataset.addSeries(series2);
        XYSeriesRenderer renderer1=new XYSeriesRenderer();
        renderer1.setColor(Color.BLUE);
        renderer1.setPointStyle(PointStyle.CIRCLE);
        renderer1.setFillPoints(true);
        renderer1.setLineWidth(3);

        renderer1.setDisplayChartValues(true);

        XYMultipleSeriesRenderer multirender=new XYMultipleSeriesRenderer();
        multirender.setXLabels(0);
        multirender.setChartTitle("student vs marks");
        multirender.setXTitle("quiz name");
        multirender.setYTitle("student mark");
        multirender.setZoomButtonsVisible(true);
        multirender.setApplyBackgroundColor(true);
        multirender.setAxesColor(Color.BLACK);
        multirender.setBarWidth(10);
        multirender.setLabelsColor(Color.RED);
        multirender.setXLabelsColor(Color.RED);
        // multirender.setYLabelsColor(1,Color.RED);
        multirender.setAxisTitleTextSize(22);
        multirender.setChartTitleTextSize(30);
        multirender.setLabelsTextSize(15);
        multirender.setBackgroundColor(Color.rgb(253,251,218));
        multirender.setMarginsColor(Color.rgb(253,251,218));







        for(int i=0;i<graphArray.size();i++){
///////////// z=name of quiz

            multirender.addXTextLabel(i, z.get(i));

        }

        multirender.addSeriesRenderer(renderer1);
        //multirender.addSeriesRenderer(renderer2);



        Intent intent= ChartFactory.getLineChartIntent(getBaseContext(),dataset,multirender);
        startActivityForResult(intent,0);
    }
    public void pie_chart(View view){
        pieChart pie = new pieChart();
        Intent lineIntent = pie.getIntent(this);
        startActivity(lineIntent);
    }
    public void get_statistics(View view)
    {
        RestAdapter adapter=new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Login.ENDPOINT)
                .build();
        RequestAPI api3=adapter.create(RequestAPI.class);
        api3.get_statistics(Login.Token,seat, new Callback<graph_get>() {
            @Override
            public void success(graph_get graphGet1, Response response) {
                // output.setText(get.getArray().get(0).getUrl().toString());
               // graphGet=graphGet1;
                graphArray=graphGet1.getQuizzes();
                OpenChart();
                //graph=graphGet1.getQuizzes().get(0);
                //System.out.println(graph.getMark());
                //System.out.println(graph.getQuiz_title());


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });}



    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "time");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            time=hourOfDay+":"+minute;

        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            date=year+"-"+(month+1)+"-"+day;
        }
    }

    public void onBackPressed() {
        logout();
    }


    public void logout() {
        DialogFragment newFragment = new Logout_student();
        newFragment.show(getFragmentManager(), "Logout");
    }

}




