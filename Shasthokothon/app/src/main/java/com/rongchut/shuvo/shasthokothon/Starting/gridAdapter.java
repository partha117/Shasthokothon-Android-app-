package com.rongchut.shuvo.shasthokothon.Starting;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rongchut.shuvo.shasthokothon.R;

/**
 * Created by ASUS on 28-Oct-16.
 */

public class gridAdapter extends BaseAdapter {

    private Context mContext;
    private  String[] name;
    private  int[] Imageid;
    private Activity activity;

    public gridAdapter(Context mContext, String[] name, int[] imageid) {
        this.mContext = mContext;
        this.name = name;
        Imageid = imageid;
        activity=(Activity)mContext;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = null;
        if (view == null) {
            // generate a view and return
            LayoutInflater inflater=activity.getLayoutInflater();
            v = inflater.inflate(R.layout.grid_layout, null);
            TextView textView = (TextView) v.findViewById(R.id.textViewGrid);
            final ImageView imageView = (ImageView)v.findViewById(R.id.imageViewGrid);
            textView.setText(name[i]);
            BitmapFactory bitmapFactory=new BitmapFactory();
            imageView.setImageBitmap(bitmapFactory.decodeResource(mContext.getResources(),Imageid[i]));









        } else {
            v=view;

            TextView textView = (TextView)v.findViewById(R.id.textViewGrid);
            final ImageView imageView = (ImageView)v.findViewById(R.id.imageViewGrid);
            textView.setText(name[i]);
            BitmapFactory bitmapFactory=new BitmapFactory();
            imageView.setImageBitmap(bitmapFactory.decodeResource(mContext.getResources(),Imageid[i]));



        }

        return v;

    }
}
