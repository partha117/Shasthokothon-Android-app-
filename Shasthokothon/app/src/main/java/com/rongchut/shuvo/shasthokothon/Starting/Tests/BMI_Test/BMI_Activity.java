package com.rongchut.shuvo.shasthokothon.Starting.Tests.BMI_Test;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Tests.AUTISM_Test.Autism_Activity;

import java.util.ArrayList;
import java.util.List;

public class BMI_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_);
        getSupportActionBar().setTitle("ওজন কি মাত্রাতিরিক্ত");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerMethod);
        final TextView inchi=(TextView)findViewById(R.id.textViewInchi);
        final EditText hInchi=(EditText)findViewById(R.id.heightInchi);
        final EditText height=(EditText)findViewById(R.id.height);
        final EditText weight=(EditText)findViewById(R.id.editTextWeight);
        Button submit=(Button)findViewById(R.id.button);


        final List<String> method = new ArrayList<>();
        method.add("ফুট");
        method.add("মিটার");
        method.add("সেন্টিমিটার");
        final String[] method_selection = {"ফুট"};
        ArrayAdapter<String> method_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, method);

        method_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(method_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                method_selection[0] = parent.getItemAtPosition(position).toString();
                if(method_selection[0].compareTo("ফুট")==0)
                {
                    inchi.setVisibility(View.VISIBLE);
                    hInchi.setVisibility(View.VISIBLE);
                }
                else
                {
                    inchi.setVisibility(View.INVISIBLE);
                    hInchi.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((height.getText().length() != 0) & (weight.getText().length() != 0)) {
                    double hf = Double.parseDouble(String.valueOf(height.getText()));
                    double hi = 0;
                    if (method_selection[0].compareTo("ফুট") == 0) {
                        int temp=hInchi.getText().length();
                        if(temp==0)
                        {
                            hi=0;
                        }
                        else {
                            hi = Double.parseDouble(String.valueOf(hInchi.getText()));
                        }
                        hf *= 0.3048;
                        hi *= 0.0254;
                        hf = hf + hi;
                    } else if (method_selection[0].compareTo("সেন্টিমিটার") == 0) {
                        hf = hf / 100;
                    }
                    double weightValue = Double.parseDouble(String.valueOf(weight.getText()));

                    String text = setText(hf, weightValue);
                    double expectedWeight[] = getWeight(hf, weightValue);

                    text += "\n" + "আপনার জন্য সঠিক ওজনের সীমা " + expectedWeight[0] + " কেজি থেকে " + expectedWeight[1] + " কেজি পর্যন্ত";

                    final Dialog dialog = new Dialog(BMI_Activity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.autism_dialoge_result);
                    TextView Title=(TextView)dialog.findViewById(R.id.textView6);
                    TextView resultText=(TextView)dialog.findViewById(R.id.textView7);
                    Button done=(Button)dialog.findViewById(R.id.button3);

                    Title.setText("ফলাফল");

                    if ((weightValue >= expectedWeight[0]) && (weightValue <= expectedWeight[1])) {
                        resultText.setTextColor(Color.parseColor("#173B06"));
                    } else {
                        resultText.setTextColor(Color.RED);
                    }
                    resultText.setText(text);


                    dialog.show();
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {

                            dialog.dismiss();
                            finish();
                        }
                    });
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                            finish();

                        }
                    });



                }
                else
                {
                    Toast.makeText(BMI_Activity.this, "সবগুলো ঘর পূরণ করা আবশ্যক", Toast.LENGTH_SHORT).show();
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

        if (id ==R.id.homeButton)
        {

            Intent intent=new Intent(BMI_Activity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(BMI_Activity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);
            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(BMI_Activity.this);
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
    String setText(double height,double weight)
    {
        double bmi=(weight)/(height*height);
        String text="";
        if(bmi<=15)
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন খুবই গুরতর মাত্রায় কম";
        }
        else if((bmi>15)&&(bmi<=16))
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন গুরতর মাত্রায় কম";
        }
        else if((bmi>16)&&(bmi<=18.5))
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন নির্দিষ্ট মাত্রার তুলনায় কম";
        }
        else if((bmi>18.5)&&(bmi<=25))
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন ঠিক আছে";
        }
        else if((bmi>25)&&(bmi<=30))
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন নির্দিষ্ট মাত্রার তুলনায় বেশী";
        }
        else if((bmi>30)&&(bmi<=35))
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন নির্দিষ্ট মাত্রার তুলনায় গুরতর মাত্রায় বেশী";
        }
        else if((bmi>35)&&(bmi<=40))
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন নির্দিষ্ট মাত্রার তুলনায় খুবই  গুরতর মাত্রায় বেশী";

        }
        else if(bmi>40)
        {
            text="বি এম আই স্কেল অনুসারে আপানার ওজন নির্দিষ্ট মাত্রার তুলনায় বিপদজনক মাত্রায় বেশী";
        }
        return text;

    }
    double[] getWeight(double height,double weightValue)
    {
        double weight[]=new double[2];
        weight[0]=Math.round((height*height)*18.5);
        weight[1]=Math.round((height*height)*25);

        return weight;

    }
}


