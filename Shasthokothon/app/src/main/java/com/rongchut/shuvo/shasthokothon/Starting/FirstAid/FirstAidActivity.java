package com.rongchut.shuvo.shasthokothon.Starting.FirstAid;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.StartingActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FirstAidActivity extends AppCompatActivity {
    ArrayList<String>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar1); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("প্রাথমিক চিকিৎসা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // toolbar.setNavigationIcon(R.mipmap.first_aid);
        ListView aidlist=(ListView)findViewById(R.id.list_first_aid);
        createArray();
        ListAdapter listAdapter=new ListAdapter(FirstAidActivity.this,R.layout.listview,R.id.textViewInList,arrayList);
        aidlist.setAdapter(listAdapter);
        aidlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendIntent(i);
                Log.d("show fa:i",i+"");
            }
        });



    }
    private  void createArray()
    {
        arrayList=new ArrayList<>();
        arrayList.add("রক্তপাত");
        arrayList.add("পোড়া");
        arrayList.add("পানিতে ডোবা");
        arrayList.add("হাড় ভাঙ্গা");
        arrayList.add("সাপে কাঁটা");
        arrayList.add("তড়িতাহত");
        arrayList.add("বিষাক্ত পোকামাকড়ের কামড়");
        arrayList.add("বিষক্রিয়া");
        arrayList.add("কুকুর জাতীয় প্রাণী দ্বারা আক্রান্ত হওয়া");
        arrayList.add(" শ্বাসকষ্ট");
        arrayList.add("সাধারন সর্দি জ্বর");
        arrayList.add(" খিঁচুনি");
        arrayList.add("উদরাময় বা ডায়রিয়া");


    }
    private void sendIntent(int i)
    {
        Intent myIntent=new Intent(FirstAidActivity.this,FirstAidShow.class);
        myIntent.putExtra("arrayList",arrayList);
        myIntent.putExtra("clickId",i);
        startActivity(myIntent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_others, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if ((id ==R.id.homeButton)||(id==android.R.id.home))
        {

            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(FirstAidActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(FirstAidActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.about_dialoge);
            TextView  link=(TextView)dialog.findViewById(R.id.textView51);
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/%E0%A6%B8%E0%A7%8D%E0%A6%AC%E0%A6%BE%E0%A6%B8%E0%A7%8D%E0%A6%A5%E0%A7%8D%E0%A6%AF%E0%A6%95%E0%A6%A5%E0%A6%A8-697701580408010/"));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {

                    }

                }
            });
            ImageView iv=(ImageView)dialog.findViewById(R.id.imageView4);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/%E0%A6%B8%E0%A7%8D%E0%A6%AC%E0%A6%BE%E0%A6%B8%E0%A7%8D%E0%A6%A5%E0%A7%8D%E0%A6%AF%E0%A6%95%E0%A6%A5%E0%A6%A8-697701580408010/"));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {

                    }
                }
            });
            dialog.show();

        }





        return super.onOptionsItemSelected(item);
    }
}
