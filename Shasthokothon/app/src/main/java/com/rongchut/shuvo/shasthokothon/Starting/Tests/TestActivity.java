package com.rongchut.shuvo.shasthokothon.Starting.Tests;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.AUTISM_Test.Autism_Activity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.BMI_Test.BMI_Activity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.CB_Test.CB_TestActivity;
import com.rongchut.shuvo.shasthokothon.Starting.gridAdapter;

public class TestActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        toolbar = (Toolbar) findViewById(R.id.tool_bar1); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        getSupportActionBar().setTitle("সাধারন পরীক্ষা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GridView gdv=(GridView)findViewById(R.id.testGrid);
        String [] name={"অটিজম পরীক্ষা","ওজন কি মাত্রাতিরিক্ত","বর্ণান্ধতা পরীক্ষা"};
        int[] imageId={R.drawable.autism,R.drawable.bmi,R.drawable.color_blind};
        gdv.setAdapter(new gridAdapter(this,name,imageId));
        gdv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    /*Intent myIntent=new Intent(TestActivity.this,MapsActivity.class);
                    startActivity(myIntent);*/


                    final Dialog dialog = new Dialog(TestActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.test_autism_start);
                    TextView header=(TextView)dialog.findViewById(R.id.textView10);
                    TextView text=(TextView)dialog.findViewById(R.id.textView14);
                    TextView alert=(TextView)dialog.findViewById(R.id.textView15);
                    TextView url=(TextView)dialog.findViewById(R.id.textView16);
                    Button done=(Button)dialog.findViewById(R.id.button6);
                    header.setText("অটিজম কি?");
                    text.setText("অটিজম কে বলা যেতে পারে কাঙ্খিত সামাজিক আচরণের অভাব।অটিজম আক্রান্ত শিশুদের অনেক সময় পাগল বলে অভিহিত করা হয়।কিন্ত সুচিকিৎসা ও সাহচার্য পেলে এরা সবার মত স্বাভাবিক জীবনযাপন করতে পারে।");
                    alert.setText("এই পরীক্ষা কোনভাবেই সুনিশ্চিত ভাবে শিশু অটিজমে আক্রান্ত কিনা তা বলে না।বরং শুধুমাত্র সম্ভাবনার কথা বলে।এবং এই পরীক্ষা ১৬-৩০ মাস বয়সী শিশুদের জন্য প্রযোজ্য।অটিজম বিষয়ে বিস্তারিত জানতে হলে নিচে ক্লিক করুন");
                    url.setText(getResources().getString(R.string.URL_AUTISM));
                    url.setMovementMethod(LinkMovementMethod.getInstance());
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            Intent myIntent=new Intent(TestActivity.this,Autism_Activity.class);
                            startActivity(myIntent);



                        }
                    });
                    dialog.show();

                }

                else if(i==1)
                {
                    Intent myIntent=new Intent(TestActivity.this,BMI_Activity.class);
                    startActivity(myIntent);


                }
                else if(i==2)
                {
                    final Dialog dialog = new Dialog(TestActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.test_autism_start);
                    TextView header=(TextView)dialog.findViewById(R.id.textView10);
                    TextView text=(TextView)dialog.findViewById(R.id.textView14);
                    TextView alert=(TextView)dialog.findViewById(R.id.textView15);
                    TextView url=(TextView)dialog.findViewById(R.id.textView16);
                    Button done=(Button)dialog.findViewById(R.id.button6);
                    header.setText("বর্ণান্ধতা কি? ");
                    text.setText("বর্ণান্ধতা  হলো মানুষের, কিছু রঙ দেখার, সনাক্ত করার বা তাদের মধ্যে পার্থক্য করার অক্ষমতা। এ সমস্যায় আক্রান্ত ব্যাক্তি কিছু রং ভুল ভাবে সনাক্ত করেন।এতে জীবনযাপনের স্বাভাবিক কাজ যেমনঃ রাস্তায় সিগন্যাল দেখে চলাফেরা করা ব্যাহত হতে পারে। ");
                    alert.setText("এই পরীক্ষায় আপনাকে কিছু ছবি দেখানো হবে যেখানে ভিন্ন রং দিয়ে কিছু ইংরেজি সংখ্যা লিখা আছে ।আপনি যদি তা দেখতে পান তবে তা নিচের বক্সে লিখুন আর না দেখতে পারলে টিক চিহ্ন দিন।সঠিক ভাবে সনাক্তকরনের জন্য সবগুলো সঠিক ভাবে পূরণ করা আবশ্যক।");
                    url.setText(getResources().getString(R.string.URL_COLOR_BLIND));

                    url.setMovementMethod(LinkMovementMethod.getInstance());
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            Intent myIntent=new Intent(TestActivity.this, CB_TestActivity.class);

                            startActivity(myIntent);

                            finish();


                        }
                    });
                    dialog.show();

                }
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

        if ((id ==R.id.homeButton)||(id==android.R.id.home))
        {

            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(TestActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(TestActivity.this);
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
