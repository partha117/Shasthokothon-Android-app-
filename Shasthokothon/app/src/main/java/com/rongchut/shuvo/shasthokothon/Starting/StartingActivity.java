package com.rongchut.shuvo.shasthokothon.Starting;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.Campaign.Diseases.DiseasesNotificationActivity;

import com.rongchut.shuvo.shasthokothon.Starting.Campaign.Link.LinkCampaignActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Campaign.VaccineTime.VaccineCampaignActivity;

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Intent intent=getIntent();
        String target=intent.getStringExtra("TARGET");
        if(target!=null)
        {
            if(target.compareTo("DISEASES")==0)
            {
                Intent intent1=new Intent(StartingActivity.this, DiseasesNotificationActivity.class);
                intent1.putExtras(intent.getExtras());
                startActivity(intent1);
                finish();
            }
            else if(target.compareTo("VACCINE")==0)
            {
                Intent intent1=new Intent(StartingActivity.this, VaccineCampaignActivity.class);
                intent1.putExtras(intent.getExtras());
                startActivity(intent1);
                finish();
            }
            else if(target.compareTo("CAMPAIGN")==0)
            {
                Intent intent1=new Intent(StartingActivity.this, LinkCampaignActivity.class);
                intent1.putExtras(intent.getExtras());
                startActivity(intent1);
                finish();
            }
        }
        else
        {
            new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    //set the new Content of your activity
                    Intent myIntent = new Intent(StartingActivity.this, MainActivity.class);
                    startActivity(myIntent);
                    StartingActivity.this.finish();

                }
            }.start();
        }
    }
}





