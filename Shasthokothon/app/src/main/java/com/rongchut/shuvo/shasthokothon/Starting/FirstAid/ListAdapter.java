package com.rongchut.shuvo.shasthokothon.Starting.FirstAid;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 29-Oct-16.
 */

public class ListAdapter extends ArrayAdapter<String> {
    Activity activity;
    ArrayList<String> arrayList;
    int resource;
    int textResource;
    TextView textView;
    public ListAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> objects) {
        super(context, resource, textViewResourceId, objects);
        activity= (Activity) context;
        arrayList=objects;
        this.resource=resource;
        textResource=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (convertView == null) {
            // generate a view and return
            LayoutInflater inflater=activity.getLayoutInflater();
            v = inflater.inflate(resource, null);
            textView=(TextView)v.findViewById(textResource);


            String e=arrayList.get(position);
            textView.setText(e);

        } else {
            v = convertView;
            textView=(TextView)v.findViewById(textResource);


            String e=arrayList.get(position);
            textView.setText(e);

        }
        return v;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }
}

