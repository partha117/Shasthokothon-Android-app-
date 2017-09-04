package com.rongchut.shuvo.shasthokothon.Starting.Tests.AUTISM_Test;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.BMI_Test.BMI_Activity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.TestActivity;

import java.util.ArrayList;

public class Autism_Activity extends AppCompatActivity {
    private boolean checks[]=new boolean[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autism);
        getSupportActionBar().setTitle("অটিজম পরীক্ষা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ListView list=(ListView)findViewById(R.id.list);
    ArrayList<String> arrayList=populate();
    Button ok=(Button)findViewById(R.id.button);
    QuestionAdapter adapter= new QuestionAdapter(this, R.layout.autism_list_cell, R.id.textView3,R.id.Yes, arrayList) {
        @Override
        public void onStatusChange(int position, boolean status) {
            checks[position]=status;

            String temp="";
            for(int i=0;i<20;i++)
            {
                temp+=checks[i]+" ,";
            }


        }
    };
    list.setAdapter(adapter);
    ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ASD_Detector detector=new ASD_Detector(checks);
            final Dialog dialog = new Dialog(Autism_Activity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.autism_dialoge_result);
            TextView Title=(TextView)dialog.findViewById(R.id.textView6);
            TextView text=(TextView)dialog.findViewById(R.id.textView7);
            Button done=(Button)dialog.findViewById(R.id.button3);

            Title.setText("ফলাফল");
            text.setText(detector.getReport());


            dialog.show();
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    dialog.dismiss();
                    finish();
                }
            });
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    finish();

                }
            });
        }
    });


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

        if (id ==R.id.homeButton)
        {
            Intent intent=new Intent(Autism_Activity.this,MainActivity.class);
            startActivity(intent);

            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(Autism_Activity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(Autism_Activity.this);
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
        else if(id==android.R.id.home)
        {
            finish();
        }




        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String >populate()
    {

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("আপনি যদি কোন দিকে আঙ্গুল দিয়ে দিক নির্দেশ করেন সে কি ঐ দিকে তাকায়?");
        arrayList.add("আপনি কি কোন সময় চিন্তা করেছেন সে বধির কি না?");
        arrayList.add("সে কি এমন কোন খেলা খেলে যেখানে অভিনয় করতে হয়(যেমন পুতুল কে খাওয়ানো,ফোনে কথা বলার ভান করা)");
        arrayList.add("সে কি চেয়ার ,সিঁড়ি অথবা অন্য কিছু চড়ার চেষ্টা করে?");
        arrayList.add("সে কি তার আঙ্গুল নিয়ে অস্বাভাবিক আচরণ করে?");
        arrayList.add("সে কি কোন সময় আঙ্গুল দিয়ে কোন দিক নির্দেশ করে(যেমন আঙ্গুল দিয়ে কোন খেলনা দেখান)?");
        arrayList.add("সে কি কোন মজার বিষয়ে আপনার মনোযোগ আকর্ষণ করার চেষ্টা করে(যেমন পাখি দেখলে আপনাকে দেখানোর চেষ্টা করা)?");
        arrayList.add("সে কি অন্য বাচ্চাদের সাথে খেলতে যায় অথবা অন্য বাচ্চা দেখলে হাসে বা কথা বলার চেষ্টা করে?");
        arrayList.add("সে কি কোন সময় আপনার  মনোযোগ আকর্ষণ করে আপনার সাথে কোন কিছু ভাগাভাগি করার জন্য(যেমন খেলনা নিয়ে  আপনার সাথে খেলার চেষ্টা)?");
        arrayList.add("আপনি নাম ধরে ডাকলে কি সাড়া দেয়?");
        arrayList.add("আপনি হাসলে কি সে ও হাসে?");
        arrayList.add("সে কি চারপাশের শব্দ দ্বারা খুব বিরক্ত হয়?");
        arrayList.add("সে কি হাটতে পারে?");
        arrayList.add("সে কি আপানার চোখের দিকে সরাসরি তাকায় যখন আপনি তার সাথে কথা বলেন বা তার সাথে খেলেন?");
        arrayList.add("সে কি অনুকরণের চেষ্টা করে?");
        arrayList.add("আপনি যদি মাথা ঘুরিয়ে দেখার চেষ্টা করেন সে ও কি তখন আপনি কি দেখছেন তা দেখার চেষ্টা করে?");
        arrayList.add("সে কি কোন সময় আপনার দৃষ্টি আকর্ষণ করে শুধুমাত্র তাকে দেখার জন্য(যেমন বলা \"আমাকে দেখ\")?");
        arrayList.add("আপনি কিছু করতে বললে বা নিষেধ করলে সে কি বুঝতে পারে?");
        arrayList.add("সে কি কোনসময় আপনার অনুভুতি বা মনোভাব বোঝার জন্য আপনার মুখের দিকে তাকায়?");
        arrayList.add("সে কি ঘুরতে পছন্দ করে বা নড়াচড়া করতে হয় এমন খেলা বা কাজ পছন্দ করে?");

        return arrayList;
    }

}
