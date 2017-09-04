package com.rongchut.shuvo.shasthokothon.Starting.DrugReaction;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback.AddFeedbackActivity;
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.ViewFeedback.ViewFeedbackActivity;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.AUTISM_Test.Autism_Activity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.BMI_Test.BMI_Activity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.CB_Test.CB_TestActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.TestActivity;
import com.rongchut.shuvo.shasthokothon.Starting.gridAdapter;

public class FeedBackActivity extends AppCompatActivity {
     Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        toolbar = (Toolbar) findViewById(R.id.tool_bar1); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ঔষধের প্রতিক্রিয়া");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GridView gdv=(GridView)findViewById(R.id.reactionGrid);
        String [] name={"ঔষধের ফলাফল","ঔষধের বিরূপ প্রতিক্রিয়া"};
        int[] imageId={R.drawable.reaction,R.drawable.chart};
        gdv.setAdapter(new gridAdapter(this,name,imageId));
        gdv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    /*Intent myIntent=new Intent(TestActivity.this,MapsActivity.class);
                    startActivity(myIntent);*/


                    final Dialog dialog = new Dialog(FeedBackActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.test_autism_start);
                    TextView header=(TextView)dialog.findViewById(R.id.textView10);
                    TextView text=(TextView)dialog.findViewById(R.id.textView14);
                    TextView alert=(TextView)dialog.findViewById(R.id.textView15);
                    TextView url=(TextView)dialog.findViewById(R.id.textView16);
                    Button done=(Button)dialog.findViewById(R.id.button6);
                    header.setText("ঔষধের ফলাফল");
                    text.setText("যদি আপনি কোন ঔষধ গ্রহন করার পর বিরূপ প্রতিক্রিয়ার সম্মুখীন হন তাহলে এখানে তা আপনি জানাতে পারেন।");
                    alert.setVisibility(View.INVISIBLE);
                    url.setVisibility(View.INVISIBLE);
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            Intent myIntent=new Intent(FeedBackActivity.this,AddFeedbackActivity.class);
                            startActivity(myIntent);




                        }
                    });
                    dialog.show();

                }

                else if(i==1)
                {



                    final Dialog dialog = new Dialog(FeedBackActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.test_autism_start);
                    TextView header=(TextView)dialog.findViewById(R.id.textView10);
                    TextView text=(TextView)dialog.findViewById(R.id.textView14);
                    TextView alert=(TextView)dialog.findViewById(R.id.textView15);
                    TextView url=(TextView)dialog.findViewById(R.id.textView16);
                    Button done=(Button)dialog.findViewById(R.id.button6);
                    header.setText("ঔষধের বিরূপ প্রতিক্রিয়া");
                    text.setText("ঔষধের কোন বিরূপ প্রতিক্রিয়া আছে কি না তা এখানে ঔষধের নাম লিখে জানতে পারেন(এর জন্য আপনার ইন্টারনেট সংযোগ প্রয়োজন)");
                    alert.setVisibility(View.INVISIBLE);
                    url.setVisibility(View.INVISIBLE);
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(isInternetAvailable()) {
                                dialog.dismiss();
                                Intent myIntent=new Intent(FeedBackActivity.this,ViewFeedbackActivity.class);
                                startActivity(myIntent);

                            }
                            else
                            {
                                Toast.makeText(FeedBackActivity.this, "বর্তমানে আপনার কোন ইন্টারনেট সংযোগ নেই", Toast.LENGTH_SHORT).show();
                            }



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
            final Dialog dialog = new Dialog(FeedBackActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(FeedBackActivity.this);
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

    public boolean isInternetAvailable() {
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            //Toast.makeText(MainActivity.this, "Network Available", Toast.LENGTH_LONG).show();
            return true;

        }
        else
        {
            //Toast.makeText(MainActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
            return false;

        }

    }
}

