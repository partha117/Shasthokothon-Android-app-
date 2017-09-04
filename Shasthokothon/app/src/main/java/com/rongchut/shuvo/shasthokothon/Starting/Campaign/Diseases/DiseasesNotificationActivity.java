package com.rongchut.shuvo.shasthokothon.Starting.Campaign.Diseases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rongchut.shuvo.shasthokothon.R;

import org.w3c.dom.Text;

public class DiseasesNotificationActivity extends AppCompatActivity {
    String diseaseName;
    String diseaseseason;
    String diseaseprecaution;
    String  diseasemessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_notification);
        Intent intent=getIntent();
        diseaseName=intent.getStringExtra("NAME");
        diseaseseason=intent.getStringExtra("SEASONAL");
        diseaseprecaution=intent.getStringExtra("PRECAUTION");
        diseasemessage=intent.getStringExtra("MESSAGE1");
        String ms2,ms3,toBeSeen;
        ms2=intent.getStringExtra("MESSAGE2");
        ms3=intent.getStringExtra("MESSAGE3");
        if((diseaseName==null)||(diseaseseason==null)||(diseaseprecaution==null)||(diseasemessage==null)) {
            finish();

        }
        else
        {
            toBeSeen=diseasemessage;
            if(ms2!=null)
            {
                toBeSeen+=ms2;
            }
            if(ms3!=null)
            {
                toBeSeen+=ms3;
            }
            TextView name = (TextView) findViewById(R.id.textView38);
            TextView season = (TextView) findViewById(R.id.textView40);
            TextView pre = (TextView) findViewById(R.id.textView42);
            TextView message = (TextView) findViewById(R.id.textView44);
            if (diseaseseason.compareTo("FALSE") == 0) {
                season.setVisibility(View.INVISIBLE);
            } else {
                season.setText(diseaseseason);
            }
            name.setText(diseaseName);
            pre.setText(diseaseprecaution);
            message.setText(toBeSeen);
        }



    }
}
