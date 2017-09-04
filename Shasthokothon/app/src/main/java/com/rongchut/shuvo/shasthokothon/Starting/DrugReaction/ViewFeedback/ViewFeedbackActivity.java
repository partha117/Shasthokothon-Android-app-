package com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.ViewFeedback;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback.AddFeedbackActivity;
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback.Feedback;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Others.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewFeedbackActivity extends AppCompatActivity {

    boolean []DataReady=new boolean[1];
    boolean[]DataCalling=new boolean[1];
    BarChart bchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        getSupportActionBar().setTitle("ঔষধের বিরূপ প্রতিক্রিয়া");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RelativeLayout graphPane=(RelativeLayout)findViewById(R.id.graph);
        final BarChart barChart=new BarChart(this);
        bchart=barChart;
        final TextView message=(TextView)findViewById(R.id.textView12);
        message.setVisibility(View.INVISIBLE);
        final ProgressBar pbar=(ProgressBar)findViewById(R.id.progressBar2);
        pbar.setVisibility(View.INVISIBLE);
        View view=(BarChart)barChart;
        view.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        graphPane.addView(view);
        final FirebaseDatabase database = Utility.getDatabase();

        final DatabaseReference myRef = database.getReference("DrugReaction");
        final HashMap<String ,ArrayList<Feedback>> databaseClient=new HashMap<String, ArrayList<Feedback>>();


        final EditText name=(EditText)findViewById(R.id.editText4);




        // Creating adapter for spinner


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    //Log.d("TAG_", "Value is: " + data);//cipro
                    ArrayList<Feedback>childList=new ArrayList<>();
                    databaseClient.put(data.getKey(),childList);
                    for (DataSnapshot data1:data.getChildren())
                    {
                        Feedback feedback=data1.getValue(Feedback.class);
                        childList.add(feedback);
                        //Log.d("TAG_", "Value is: " + feedback);
                    }
                    DataReady[0]=true;
                    if(DataCalling[0])
                    {
                        pbar.setVisibility(View.INVISIBLE);
                        message.setVisibility(View.INVISIBLE);
                        Capsule capsule= new Calculation(databaseClient, name.getText().toString()).getData();
                        BarData bd =capsule.getBarData();
                        if(capsule.getStatus()==0)
                        {
                            message.setVisibility(View.VISIBLE);

                        }
                        if (bd != null) {
                            setData(bd);
                            DataCalling[0]=false;
                        }
                    }
                }
                //Log.d("TAG_", "Value is: " + store);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG_",databaseError.getDetails());

            }


        });
        Button button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setVisibility(View.INVISIBLE);
                if(name.getText().length()!=0)
                {
                    DataCalling[0]=true;
                    if(!DataReady[0])
                    {
                        pbar.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        pbar.setVisibility(View.INVISIBLE);
                        Capsule capsule= new Calculation(databaseClient, name.getText().toString()).getData();
                        BarData bd =capsule.getBarData();
                        if(capsule.getStatus()==0)
                        {
                            message.setVisibility(View.VISIBLE);
                            bchart.setVisibility(View.INVISIBLE);

                        }
                        if (bd != null) {
                            setData(bd);
                            DataCalling[0]=false;
                            Snackbar.make(findViewById(android.R.id.content), "চার্টটি আপনি  বড় করতে পারেন", Snackbar.LENGTH_SHORT)
                                    .show();
                        }
                    }
                }
            }
        });


        barChart.animateXY(3000, 3000);
        barChart.getViewPortHandler().setMinimumScaleX(1.5f);
        barChart.invalidate();


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
            Intent intent=new Intent(ViewFeedbackActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(ViewFeedbackActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);

            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(ViewFeedbackActivity.this);
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
    void setData(BarData barData)
    {
        bchart.setVisibility(View.VISIBLE);
        bchart.setData(barData);
        bchart.notifyDataSetChanged();
        //bchart.requestFocus();
        bchart.invalidate();
    }

}
