package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 04-Nov-16.
 */

public class ListViewAdapter2 extends ArrayAdapter<VaccineState> {

    Activity activity;
    ArrayList<VaccineState> arrayList;
    int resource;
    int textResource;
    CheckedTextView textView;

    public ListViewAdapter2(Context context, int resource, int textViewResourceId, ArrayList<VaccineState> objects) {
        super(context, resource, textViewResourceId, objects);
        activity= (Activity) context;
        arrayList=objects;
        this.resource=resource;
        textResource=textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (convertView == null) {
            // generate a view and return
            LayoutInflater inflater=activity.getLayoutInflater();
            v = inflater.inflate(resource, null);
            textView=(CheckedTextView) v.findViewById(textResource);


            String e=arrayList.get(position).getVaccineName();
            if(e.compareTo("এইচ.পি.ভি")==0)
            {
                textView.setText("জরায়ু ক্যান্সার(H.P.V)");
            }
            else
            {
                textView.setText(e);
            }

            Log.e("vaccine",e+"  "+arrayList.get(position).getVaccineState());
            if(arrayList.get(position).getVaccineState().compareTo("true")==0)
            {
                textView.setChecked(true);
            }
            else if(arrayList.get(position).getVaccineState().compareTo("false")==0)
            {
                textView.setChecked(false);
            }
            //textView.setChecked(true);

        } else {
            v = convertView;
            textView=(CheckedTextView) v.findViewById(textResource);


            String e=arrayList.get(position).getVaccineName();
            textView.setText(e);
            Log.e("vaccine",e+"  "+arrayList.get(position).getVaccineState());
            if(arrayList.get(position).getVaccineState().compareTo("true")==0)
            {
                textView.setChecked(true);
            }
            else if(arrayList.get(position).getVaccineState().compareTo("false")==0)
            {
                textView.setChecked(false);
            }
            //textView.setChecked(true);

        }
        return v;
    }

    public void setArrayList(ArrayList<VaccineState> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<VaccineState> getArrayList() {
        return arrayList;
    }
}
