package com.rongchut.shuvo.shasthokothon.Starting.FirstAid;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FirstAidShow extends AppCompatActivity {
    ArrayList<String> list;
    int clicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        clicked=intent.getIntExtra("clickId",0);
       // Log.d("show: clicked",clicked+"");
        list=intent.getStringArrayListExtra("arrayList");
        String name=list.get(clicked);
        StringBuilder text = new StringBuilder();
        String line;
        TextView front=(TextView)findViewById(R.id.textViewFirstAidShow);
        TextView inside=(TextView)findViewById(R.id.textViewInsideFirstAidShow);
        ImageView image=(ImageView)findViewById(R.id.imageView7);
        image.setImageBitmap(getBitmapFromAsset(this,clicked));

        try {
            InputStream is = getAssets().open(getFile(clicked));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            front.setText(name);
            getSupportActionBar().setTitle(name);
            inside.setText(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getFile(int i)
    {
        String send="firstAid/";
        switch (i)
        {
            case 0: return send+"Bleeding.txt";
            case 1: return send+"Burn.txt";
            case 2: return send+"Drowning.txt";
            case 3: return send+"Fracture.txt";
            case 4: return send+"SnakeBite.txt";
            case 5: return send+"Electrocution.txt";
            case 6: return send+"InsectBite.txt";
            case 7: return send+"poisoning.txt";
            case 8: return send+"DogBite.txt";
            case 9: return send+"Asthma.txt";
            case 10: return send+"Fever.txt";
            case 11: return send+"Spasm.txt";
            case 12: return send+"Diarrhea.txt";
            default:return null;
        }
    }
    private String getImageFile(int i)
    {
        String send="firstAidImages/";
        switch (i)
        {
            case 0: return send+"Bleeding.png";
            case 1: return send+"Burn.png";
            case 2: return send+"Drowning.png";
            case 3: return send+"Fracture.png";
            case 4: return send+"SnakeBite.png";
            case 5: return send+"Electrocution.png";
            case 6: return send+"InsectBite.png";
            case 7: return send+"poisoning.png";
            case 8: return send+"DogBite.png";
            case 9: return send+"Asthma.png";
            case 10: return send+"Fever.png";
            case 11: return send+"Spasm.png";
            case 12: return send+"Diarrhea.png";
            default:return null;
        }
    }

    private Bitmap getBitmapFromAsset(Context context,int id) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(getImageFile(id));
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
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

            Intent intent=new Intent(FirstAidShow.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(FirstAidShow.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(FirstAidShow.this);
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



}
