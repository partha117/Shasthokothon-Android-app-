package com.rongchut.shuvo.shasthokothon.Starting.Medicine;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Database.DataBase;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Others.Converter;

import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Medicine_ItemActivity extends AppCompatActivity {
    boolean checkState;
    boolean prevState;
    String medId;


    @Override
    public void onBackPressed() {

        isChanged();


        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine__item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView name=(TextView)findViewById(R.id.textView18);
        TextView qty=(TextView)findViewById(R.id.textView20);
        TextView start=(TextView)findViewById(R.id.textView22);
        TextView end=(TextView)findViewById(R.id.textView24);
        TextView timeMorning=(TextView)findViewById(R.id.textView26);
        TextView timeNoon=(TextView)findViewById(R.id.textView28);
        TextView timeNight=(TextView)findViewById(R.id.textView30);
        TextView textN=(TextView)findViewById(R.id.textView34);
        TextView textNState= (TextView)findViewById(R.id.textView35);
        CheckBox sound=(CheckBox)findViewById(R.id.checkBox2);
        Button ok=(Button)findViewById(R.id.button5);

        int id=getIntent().getIntExtra("ID",-1);
        medId=id+"";
        if(id!=-1)
        {
            DataBase db=new DataBase(this);
            String []result=db.getAllFromId(id+"");
            name.setText(result[0]);
            getSupportActionBar().setTitle("ঔষধ : "+result[0]);
            qty.setText(readyQuantity(result));
            start.setText(Converter.convertToBengali(result[4]));
            end.setText(Converter.convertToBengali(result[5]));
            timeMorning.setText(Converter.readyTime(result[6]));
            timeNoon.setText(Converter.readyTime(result[7]));
            timeNight.setText(Converter.readyTime(result[8]));
            prevState=readyBool(result[9]);
            if(readyBool(result[9]))
            {
                sound.setChecked(readyBool(result[9]));
                textN.setVisibility(View.INVISIBLE);
                textNState.setVisibility(View.INVISIBLE);
            }
            else
            {
                sound.setVisibility(View.INVISIBLE);
            }


        }
        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                checkState=b;

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChanged();
                finish();
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
            Intent intent=new Intent(Medicine_ItemActivity.this,MainActivity.class);
            startActivity(intent);

            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(Medicine_ItemActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(Medicine_ItemActivity.this);
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

    boolean  readyBool(String  string)
    {
        if(string.compareTo("true")==0)
        {
            checkState=true;
            return true;
        }
        checkState=false;
        return false;
    }
    String readyQuantity(String [] result)
    {
        String  temp="";

        temp= Converter.convertToBengali(result[1])+" টি+ "+Converter.convertToBengali(result[2])+" টি+ "+Converter.convertToBengali(result[3])+" টি";

        return temp;
    }
    void  isChanged()
    {
        if(checkState!=prevState) {
            DataBase dataBase = new DataBase(Medicine_ItemActivity.this);
            dataBase.update(medId);
        }

    }

}
