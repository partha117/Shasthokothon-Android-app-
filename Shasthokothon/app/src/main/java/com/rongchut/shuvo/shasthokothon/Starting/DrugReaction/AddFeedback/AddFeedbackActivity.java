package com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.AddFeedback;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rongchut.shuvo.shasthokothon.R;
import com.rongchut.shuvo.shasthokothon.Starting.FirstAid.FirstAidActivity;
import com.rongchut.shuvo.shasthokothon.Starting.MainActivity;
import com.rongchut.shuvo.shasthokothon.Starting.Others.Utility;

public class AddFeedbackActivity extends AppCompatActivity {
    private  CheckBox cn;
    private  CheckBox ch;
    private  CheckBox cdr;
    private  CheckBox cdi;
    private  CheckBox cit;
    private  CheckBox cr;
    private  CheckBox cin;
    private  CheckBox cs;
    private  EditText en;
    private  EditText eo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);
        getSupportActionBar().setTitle("ঔষধের ফলাফল");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final CheckBox Nausea = (CheckBox) findViewById(R.id.checkBox16);
        final CheckBox Headache = (CheckBox) findViewById(R.id.checkBox12);
        final CheckBox Diarrhoea = (CheckBox) findViewById(R.id.checkBox11);
        final CheckBox DifficultyInBreathing = (CheckBox) findViewById(R.id.checkBox15);
        final CheckBox Itching = (CheckBox) findViewById(R.id.checkBox14);
        final CheckBox Rash = (CheckBox) findViewById(R.id.checkBox10);
        final CheckBox Inflammation = (CheckBox) findViewById(R.id.checkBox9);
        final CheckBox state = (CheckBox) findViewById(R.id.checkBox13);
        final EditText others = (EditText) findViewById(R.id.editText2);
        final EditText name = (EditText) findViewById(R.id.editText);
        cn=Nausea;
        ch=Headache;
        cdr=Diarrhoea;
        cdi=DifficultyInBreathing;
        cit=Itching;
        cr=Rash;
        cin=Inflammation;
        cs=state;
        eo=others;
        en=name;
        Button ok = (Button) findViewById(R.id.button);


        final FirebaseDatabase database = Utility.getDatabase();
        final DatabaseReference myRef = database.getReference("DrugReaction");



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().length() != 0) {

                    Feedback feedback = new Feedback(Diarrhoea.isChecked() + "",
                            DifficultyInBreathing.isChecked() + "",
                            Headache.isChecked() + "",
                            Inflammation.isChecked() + "",
                            Itching.isChecked() + "",
                            Nausea.isChecked() + "",
                            others.getText().toString(),
                            Rash.isChecked() + "",
                            state.isChecked() + ""
                    );
                    //DatabaseReference myRef1 = myRef.child(feedback.getDrugName()); //Write your child reference if any
                    myRef.child(name.getText().toString()).push().setValue(feedback);
                    allClear();
                    Toast.makeText(AddFeedbackActivity.this, "আপনার তথ্য গ্রহন করা হয়েছে", Toast.LENGTH_SHORT).show();

                /*Intent intent=new Intent(AddFeedbackActivity.this,AddFeedbackActivity.class);
                startActivity(intent);
*/
                }
                else
                {
                    Toast.makeText(AddFeedbackActivity.this, "ঔষধের নাম আবশ্যক", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    private void allClear()
    {

        cn.setChecked(false);
        ch.setChecked(false);
        cdr.setChecked(false);
        cdi.setChecked(false);
        cit.setChecked(false);
        cr.setChecked(false);
        cin.setChecked(false);
        cs.setChecked(false);
        en.setText("");
        eo.setText("");
        return;


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
            Intent intent=new Intent(AddFeedbackActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if (id ==R.id.action_help)
        {
            final Dialog dialog = new Dialog(AddFeedbackActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialoge);

            TextView textView=(TextView)dialog.findViewById(R.id.textView36);
            textView.setText(getResources().getString(R.string.help_content));
            dialog.show();

        }
        else if(id==R.id.action_about)
        {
            final Dialog dialog = new Dialog(AddFeedbackActivity.this);
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

