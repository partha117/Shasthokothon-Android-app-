package com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.ViewFeedback;

import android.graphics.Color;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback.Feedback;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ASUS on 07-Jan-17.
 */

public class Calculation {


    private  int total;
    private HashMap<String, ArrayList<Feedback>> map;
    private String  target;
    private int  State;
    private int Itching;
    private int Rash;
    private int DifficultyInBreathing;
    private int Nausea;
    private int Headache;
    private int Diarrhoea;
    private  int Inflammation;
    private  int Others;

    public Calculation(HashMap<String, ArrayList<Feedback>> map, String target) {
        this.map = map;
        this.target = target;
    }

    public HashMap<String, ArrayList<Feedback>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, ArrayList<Feedback>> map) {
        this.map = map;
    }
    public Capsule getData()
    {
        ArrayList<BarEntry>entry=new ArrayList<>();
        ArrayList<Feedback>feedbacks= map.get(target);
        if(feedbacks!=null) {
            total = feedbacks.size();
            if(total==0)
            {
                return new Capsule(null,0);
            }
            for (int i = 0; i < feedbacks.size(); i++) {
                if (getBool(feedbacks.get(i).getState())) {
                    State += 1;
                }
                if (getBool(feedbacks.get(i).getItching())) {
                    Itching += 1;
                }
                if (getBool(feedbacks.get(i).getRash())) {
                    Rash += 1;
                }
                if (getBool(feedbacks.get(i).getDifficultyInBreathing())) {
                    DifficultyInBreathing += 1;
                }
                if (getBool(feedbacks.get(i).getNausea())) {
                    Nausea += 1;
                }
                if (getBool(feedbacks.get(i).getHeadache())) {
                    Headache += 1;
                }
                if (getBool(feedbacks.get(i).getDiarrhoea())) {
                    Diarrhoea += 1;
                }
                if (getBool(feedbacks.get(i).getInflammation())) {
                    Inflammation += 1;
                }
                if (feedbacks.get(i).getOthers().length() != 0) {
                    Others += 1;
                }
            }


            if (getPercent(State) != 0) {
                entry.add(new BarEntry(getPercent(State),0));
            }
            if (getPercent(Rash) != 0) {
                entry.add(new BarEntry(getPercent(Rash),1));
            }
            if (getPercent(Itching) != 0) {
                entry.add(new BarEntry(getPercent(Itching),2));
            }
            if (getPercent(DifficultyInBreathing) != 0) {
                entry.add(new BarEntry(getPercent(DifficultyInBreathing),3));
            }

            if (getPercent(Nausea) != 0) {
                entry.add(new BarEntry(getPercent(Nausea),4));
            }
            if (getPercent(Headache) != 0) {
                entry.add(new BarEntry(getPercent(Headache),5));
            }
            if (getPercent(Diarrhoea) != 0) {
                entry.add(new BarEntry(getPercent(Diarrhoea),6));
            }
            if (getPercent(Inflammation) != 0) {
                entry.add(new BarEntry(getPercent(Inflammation),7));
            }
            if (getPercent(Others) != 0) {
                entry.add(new BarEntry(getPercent(Others),8));
            }
            BarDataSet dataSet = new BarDataSet(entry, "উপসর্গ সমূহ");
            ArrayList<String > arrayList=new ArrayList<>();

            arrayList.add("সুস্থ হয়েছে");
            arrayList.add("ফুসকুড়ি");
            arrayList.add("চুলকানি");
            arrayList.add("শ্বাস নিতে সমস্যা");
            arrayList.add("বমি বমি ভাব");
            arrayList.add("মাথা ব্যাথা");
            arrayList.add("ডায়রিয়া");
            arrayList.add("গা জ্বালাপোড়া করা");
            arrayList.add("অন্যান্");


            BarData barData = new BarData(arrayList,dataSet);





            ArrayList<Integer> colors = new ArrayList<Integer>();
            colors.add(Color.parseColor("#42f4f1"));
            colors.add(Color.parseColor("#42f456"));
            colors.add(Color.parseColor("#42b0f4"));
            colors.add(Color.parseColor("#58609e"));
            colors.add(Color.parseColor("#631d54"));
            colors.add(Color.parseColor("#4c631d"));
            colors.add(Color.parseColor("#c1325b"));
            colors.add(Color.parseColor("#b948db"));
            colors.add(Color.parseColor("#e07431"));


            dataSet.setColors(colors);
            dataSet.setLabel("উপসর্গ সমূহ");




            return new Capsule(barData,feedbacks.size());
        }
        else
        {
            return new Capsule(null,0);
        }

    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    private boolean getBool(String st)
    {
        if(st.compareTo("true")==0)
        {
            return  true;
        }
        return  false;
    }
    private float getPercent(int num)
    {
        float d=num;
        d=d/total;
        d=d*100;
        return d;
    }
}
