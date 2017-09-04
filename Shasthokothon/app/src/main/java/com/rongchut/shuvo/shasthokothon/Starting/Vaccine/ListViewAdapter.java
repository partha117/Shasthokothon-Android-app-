package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 02-Nov-16.
 */

public class ListViewAdapter extends ArrayAdapter<NameData> {

    Activity activity;
    ArrayList<NameData> arrayList;
    int resource;
    int textResource;
    TextView textView;
    public ListViewAdapter(Context context, int resource, int textViewResourceId, ArrayList<NameData> objects) {
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
            textView=(TextView)v.findViewById(textResource);


            String e=arrayList.get(position).getName();
            textView.setText(e);

        } else {
            v = convertView;
            textView=(TextView)v.findViewById(textResource);


            String e=arrayList.get(position).getName();
            textView.setText(e);

        }
        return v;
    }

    public void setArrayList(ArrayList<NameData> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<NameData> getArrayList() {
        return arrayList;
    }
}

