package com.rongchut.shuvo.shasthokothon.Starting.Campaign.Link;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.rongchut.shuvo.shasthokothon.R;

public class LinkCampaignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_campaign);
        TextView text=(TextView)findViewById(R.id.textView52);
        TextView link=(TextView)findViewById(R.id.textView57);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        Intent intent=getIntent();
        String news=intent.getStringExtra("TEXT1");
        String news2=intent.getStringExtra("TEXT2");
        String news3=intent.getStringExtra("TEXT3");
        String url=intent.getStringExtra("LINK");
        if((news==null)||(url==null))
        {
            finish();
        }
        else
        {
            if(news2!=null)
            {
                news+=news2;
            }
            if(news3!=null)
            {
                news+=news3;
            }
            text.setText(news);
            link.setText(processLink(url));
        }
    }
    private  String  processLink(String st)
    {
        String temp="বিস্তারিত জানতে ক্লিক করুন "+st;
        return  temp;
    }
}
