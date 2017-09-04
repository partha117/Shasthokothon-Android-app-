package com.rongchut.shuvo.shasthokothon.Starting.Campaign.VaccineTime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rongchut.shuvo.shasthokothon.R;

public class VaccineCampaignActivity extends AppCompatActivity {
    String vaccineName;
    String vaccineAgeFrom;
    String vaccineAgeTo;
    String  vaccineDateFrom;
    String  vaccineDateTo;
    String  vaccineMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_campaign);
        Intent intent=getIntent();
        vaccineName=intent.getStringExtra("NAME");
        vaccineAgeFrom=intent.getStringExtra("AGE_FROM");
        vaccineAgeTo=intent.getStringExtra("AGE_TO");
        vaccineDateFrom=intent.getStringExtra("DATE_FROM");
        vaccineDateTo=intent.getStringExtra("DATE_TO");
        vaccineMessage=intent.getStringExtra("MESSAGE1");
        String ms2,ms3,toBeSeen;
        ms2=intent.getStringExtra("MESSAGE2");
        ms3=intent.getStringExtra("MESSAGE3");

        TextView name=(TextView)findViewById(R.id.textView43);
        TextView age=(TextView)findViewById(R.id.textView46);
        TextView dateFrom=(TextView)findViewById(R.id.textView48);
        TextView dateTo=(TextView)findViewById(R.id.textView50);
        TextView message=(TextView)findViewById(R.id.textView44);
        if((vaccineName==null)||(vaccineAgeTo==null)||(vaccineAgeFrom==null)||(vaccineDateFrom==null)||(vaccineDateTo==null)||(vaccineMessage==null))
        {
            finish();
        }
        else
        {
            toBeSeen=vaccineMessage;
            if(ms2!=null)
            {
                toBeSeen+=ms2;
            }
            if(ms3!=null)
            {
                toBeSeen+=ms3;
            }
            name.setText(vaccineName);
            age.setText(vaccineAgeFrom+" থেকে "+vaccineAgeTo);
            dateFrom.setText(vaccineDateFrom);
            dateTo.setText(vaccineDateTo);
            message.setText(toBeSeen);
        }




    }
}
