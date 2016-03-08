package com.example.fady.quiz;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import java.util.ArrayList;


/**
 * Created by Reem on 2/11/2015.
 */
public class pieChart {
    public Intent getIntent(Context context) {

        ArrayList<String> a=new ArrayList<String>();
        a.add(0,"student above 10 =");
        a.add(1,"students above 8 =");
        a.add(2,"students above 6 =");
        a.add(3,"students above 5 =");
        a.add(4,"students under 5 =");

        ArrayList<Integer> b=new ArrayList<Integer>();
        b.add(0,20);
        b.add(1,5);
        b.add(2,120);
        b.add(3,20);
        b.add(4,40);
        int x=0;

        for (int i=0;i<b.size();i++){
            x+=b.get(i);
        }

        int[] values = { 1,2,3,4,5 };
        CategorySeries series = new CategorySeries("Pie Graph");
        int k ;
        for (k=0;k<a.size();k++) {
            series.add(a.get(k)+(b.get(k)*100)/x+"% ", b.get(k));

        }

        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.CYAN };

        DefaultRenderer renderer = new DefaultRenderer();
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            ;
        }

        renderer.setChartTitle("student marks evaluation");
        renderer.setChartTitleTextSize(40);
        renderer.setLabelsTextSize(30);
        renderer.setLegendTextSize(25);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setZoomButtonsVisible(true);
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.rgb(253,251,218));

        Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Pie");
        return intent;
    }
}


