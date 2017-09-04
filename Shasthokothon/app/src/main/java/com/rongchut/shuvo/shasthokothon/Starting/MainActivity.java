package com.rongchut.shuvo.shasthokothon.Starting;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.FeedBackActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Emergency.Emergency;
import com.rongchut.shuvo.shasthokothon.Starting.Emergency.EmergencyContact.ContactActivity;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Medicine.MedicineActivity1;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.TestActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Vaccine.VaccineActivity;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private  String [] name={"প্রাথমিক চিকিৎসা","সাধারন পরীক্ষা","টিকা","জরুরী যোগাযোগ","ঔষধ","ঔষধ প্রতিক্রিয়া"};
    private  int [] imageid={R.mipmap.first_aid,R.drawable.normal_test,R.drawable.vaccine_reminder,R.drawable.emergency,R.drawable.reminder,R.drawable.reaction_response};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar1); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        GridView gv=(GridView)findViewById(R.id.Grid_view);

        gv.setAdapter(new gridAdapter(this,name,imageid));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:

                            Intent myIntent0=new Intent(MainActivity.this,FirstAidActivity.class);
                            startActivity(myIntent0);
                            return;
                    case 1:
                            Intent myIntent1=new Intent(MainActivity.this,TestActivity.class);
                            startActivity(myIntent1);
                            return;
                    case 2:
                            Intent myIntent2=new Intent(MainActivity.this,VaccineActivity.class);
                            startActivity(myIntent2);
                            return;
                    case 3:Intent myIntent3=new Intent(MainActivity.this,Emergency.class);
                           startActivity(myIntent3);
                           return;

                    case 4:
                            Intent myIntent4=new Intent(MainActivity.this, MedicineActivity1.class);
                            startActivity(myIntent4);
                            return;
                    case 5:
                        Intent myIntent5=new Intent(MainActivity.this, FeedBackActivity.class);
                        startActivity(myIntent5);
                        return;
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(MainActivity.this);
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

